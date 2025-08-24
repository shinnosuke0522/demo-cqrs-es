package com.github.shinnosuke0522.demo.cqrs.es.domain.shared.util

open class MarkdownText(val value: String) {
    init {
        require(value.isNotBlank()) { "MarkdownText cannot be blank" }
        require(value.length <= MAX_LEN) { "MarkdownText cannot be longer than $MAX_LEN characters" }
    }

    companion object {
        const val MAX_LEN = 100_000

        fun of(raw: String): MarkdownText {
            val normalized = raw.replace("\r\n", "\n")
            return MarkdownText(normalized)
        }
    }
}