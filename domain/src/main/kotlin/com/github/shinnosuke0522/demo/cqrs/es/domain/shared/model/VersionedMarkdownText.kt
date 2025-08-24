package com.github.shinnosuke0522.demo.cqrs.es.domain.shared.model

import java.nio.charset.StandardCharsets
import java.security.MessageDigest
import java.time.Instant

@ConsistentCopyVisibility
data class VersionedMarkdownText private constructor(
    val content: String,
    /** 古い→新しい順の履歴（最後の要素が最新） */
    val versionMeta: List<VersionMeta>,
) : MarkdownText(content) {

    /* ---------- メタ情報 ---------- */
    data class VersionMeta(
        val version: Int,
        // ユーザIDやメール等。必要に応じてVO化可。
        val updatedBy: String,
        val updatedAt: Instant,
        val hash: ContentHash
    )

    @JvmInline
    value class ContentHash private constructor(val value: String) {
        companion object {
            fun ofSha256(text: String): ContentHash {
                val md = MessageDigest.getInstance("SHA-256")
                val bytes = md.digest(text.toByteArray(StandardCharsets.UTF_8))
                return ContentHash(bytes.joinToString(separator = "") { "%02x".format(it) })
            }
        }
        override fun toString(): String = value
    }

    /* ---------- 現在版／整合性 ---------- */
    fun currentMeta(): VersionMeta = versionMeta.last()

    /** 本文と最新メタのハッシュが一致するか（永続化の健全性チェックに） */
    fun verifyIntegrity(): Boolean = currentMeta().hash == ContentHash.ofSha256(content)

    /** 他のオブジェクトと内容が変わっているか（ハッシュ比較） */
    fun hasChanged(other: VersionedMarkdownText): Boolean =
        this.currentMeta().hash != other.currentMeta().hash

    /* ---------- 変更適用（差分があれば昇番） ---------- */
    fun update(
        newRawContent: String,
        updatedBy: String,
        at: Instant = Instant.now(),
        maxLen: Int = MAX_LEN
    ): VersionedMarkdownText {
        // 常にファクトリを通して正規化・検証
        val normalized = MarkdownText(newRawContent)
        val newHash = ContentHash.ofSha256(normalized.value)
        val cur = currentMeta()

        // 差分なしならそのまま返却（バージョンを進めない）
        if (newHash == cur.hash) return this

        val nextMeta = cur.copy(
            version = cur.version + 1,
            updatedBy = updatedBy,
            updatedAt = at,
            hash = newHash
        )
        return copy(content = normalized.value, versionMeta = versionMeta + nextMeta)
    }

    /* ---------- ファクトリ ---------- */
    companion object {
        /**
         * 初期版を生成。
         * - 生文字列を受け取り、CRLF→LF/UTF-8整合/長さの検証を実施
         * - version=1 で作成し、ハッシュを計算して履歴に格納
         */
        fun initialOf(
            rawContent: String,
            updatedBy: String,
            at: Instant = Instant.now(),
            maxLen: Int = MAX_LEN
        ): VersionedMarkdownText {
            val normalized = MarkdownText(rawContent).value
            val hash = ContentHash.ofSha256(normalized)
            val meta = VersionMeta(
                version = 1,
                updatedBy = updatedBy,
                updatedAt = at,
                hash = hash
            )
            return VersionedMarkdownText(content = normalized, versionMeta = listOf(meta))
        }

        /**
         * すでに「正規化済みコンテンツ」と整合性が取れたメタを外部から与えて復元する場合に使用。
         * 例：永続層→ドメイン復元。呼び出し側で一貫性を担保すること。
         */
        fun fromTrustedNormalized(
            normalizedContent: String,
            versionMeta: List<VersionMeta>
        ): VersionedMarkdownText {
            require(normalizedContent.indexOf('\r') == -1) {
                "Content must be LF-normalized (no CRLF)."
            }
            require(versionMeta.isNotEmpty()) { "versionMeta must not be empty." }
            return VersionedMarkdownText(normalizedContent, versionMeta)
        }
    }
}