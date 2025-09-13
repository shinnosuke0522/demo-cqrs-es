# コーディング規約

## ドメインの実装

本規約は当リポジトリの現行実装（domain-base / review-domain / organization-domain / user-domain / libraries/fundamentals）を分析し、整合性と保守性を高めるための実践ルールをまとめたものです。以降のルールは新規実装・既存コードの修正時の判断基準とします。

### Entity のコーディングルール
- 継承元と役割
  - すべての集約ルートは `AggregateRoot<ID>` を継承する（例: `Proposal`, `ReviewAssignment`, `Organization`）。
  - ライフサイクルを持つ場合は `sealed class` で状態を表現し、状態遷移を明確化する（例: `Awaiting` → `Approved` → `Completed`）。
- 不変性と生成
  - `data class` を基本とし、公開プロパティは不変（`val`）で定義する。
  - 生成はコンパニオンの `of(...)`／`generate()`／状態遷移のファクトリで行い、副作用（ドメインイベント追加）を `apply { addDomainEvent(...) }` で内包する。
  - 可能な限りプリミティブを避け、ID や VO（値オブジェクト）を用いる。
- ドメインイベント
  - すべての状態遷移で必要なイベントを発行し、`AggregateRoot` の `addDomainEvent` を通じて蓄積する。
  - イベントの `occurredAt` はイベント ID（ULID）から復元して一貫性を保つ。
- メソッド設計
  - 仕様上の可変操作は「新しいインスタンスを返す」関数（`withXxx`, `updateXxx`, `submit`, `decide` 等）として実装する。
  - 例外による分岐は避け、検証や遷移可否は `Either<Error, Entity>` で明示的に返す（状態遷移が複雑な場合）。
- 例
  - `ApprovedAssignment.of(awaiting, approvedAt)` のように、入力状態を受けて次状態を構築し、イベント発行を内包するパターンを準拠とする。

### VO（Value Object）のコーディングルール
- 目的とスタイル
  - ルールや整形を内包する小さな型で、プリミティブな `String`/`Instant`/`Int` 等を直接露出しない。
  - できる限り `@JvmInline value class` を用い、不要なアロケーションを避ける。
- 生成と検証
  - ドメインロジックを含む場合、公開コンストラクタは避け、`private constructor` とし、`companion object` から `of(...)` を提供する。
  - 入力検証を行い、失敗時は `Either<DomainError, VO>` を返す（例: `Email.of`, `OrganizationName.of`, `ProposalTitle.of`）。
  - 正規化が必要な文字列は VO 内で行う（例: Markdown は `MarkdownText.of` で CRLF→LF へ正規化）。
- 比較・表示
  - 順序が必要な ID/VO は `Comparable` を実装し、内部値の比較に委譲する。
  - ID は中身の `String`/`ULID` を直接公開しない。必要なら `toString()` や明示メソッド（例: `ULID.value()`）で限定的に提供する。
- バージョン付きの VO
  - 版管理が必要なテキストは `VersionedMarkdownText` を使用する。
  - 生成・更新時は必ず `MarkdownText.of(...)` による正規化を通し、ハッシュ（SHA-256）で改変有無を判定し、同一内容では版を進めない。

### Repository のコーディングルール
- 契約（ポート）
  - ドメイン層の Repository は `interface Repository<E, ID>` を継承する（`exists`, `findById`, `findByIds`, `save`）。
  - API は全て `suspend` とし、非同期境界を統一する（例外: 明確に同期 I/O を持たないテストダブル）。
  - `findById` は `Option<E>` を返し、存在しないことを例外では表現しない。
  - クエリメソッドの引数・戻り値には VO/ID を用い、`String` 等のプリミティブを避ける（例: `ProposalId`, `ProgramCommitteeId`）。
- 実装（アダプタ）
  - インフラ詳細（JPA, jOOQ, Mongo など）はドメイン層に漏らさない。
  - リポジトリ実装は `Either` を返さず、ドメインサービス側で組み合わせる。I/O エラー等の外部失敗はアプリケーション層でハンドリングする。

### ドメインエラーの実装ルール
- 共通基盤
  - すべてのドメイン固有エラーは `DomainError` を実装する `sealed interface` の配下に置く（例: `UserError`, `OrganizationError`, `ReviewAssignmentError`）。
  - `DomainError` は `message: String` と `cause: DomainError.Cause` を持つ。`Cause` は `None` / `Domain(error)` / `External(throwable)` を用意し、連鎖を明示できるようにする。
- 定義方法
  - 具体的なエラーは `data class XxxError(override val message: String, override val cause: DomainError.Cause = None)` として定義する。
  - 例外（`Throwable`）のスローでドメインルール違反を表現しない。関数の戻り値に `Either<Error, T>` を用いる。
- エラーメッセージ
  - 利用者が次のアクションを判断できる具体的な文言にする（ID・状態・制約値など）。
  - ログやイベントに出す可能性を考慮し、個人情報や秘匿情報は含めない。
- 運用指針
  - ドメイン→ドメインの失敗連鎖は `Cause.Domain`、I/O 等の外部要因は `Cause.External` で結び、トレーサビリティを担保する。

---
補足: イベント ID には ULID を用い、`occurredAt` は ID のタイムスタンプから一貫して再構成する。`Option` を用いて `null` を回避し、原則としてドメイン層では `null` を受け入れない。

