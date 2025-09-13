# AGENTS.md

このリポジトリは Kotlin（マルチモジュール Gradle）でドメイン駆動設計を採用しています。AI コーディングエージェントとして作業する際は、以下のプロジェクト固有ガイドラインに従ってください。

## 適用範囲
- 本ファイルの指示はリポジトリ全体に適用されます。
- ドメイン実装に関する詳細なコーディング規約は必ず `docs/coding_practice.md` を参照・遵守してください（エンティティ、VO、リポジトリ、ドメインエラーの規約のソース・オブ・トゥルース）。

## リポジトリ構成
- ルートの Version Catalog: `gradle/libs.versions.toml`
- モジュール:
  - `domain/domain-base`: 共有ドメイン基盤（AggregateRoot, DomainEvent, 共通VO 等）
  - `domain/review-domain`, `domain/organization-domain`, `domain/user-domain`: 各バウンデッドコンテキスト
  - `libraries/fundamentals`: 横断的ユーティリティ（ULID, Arrow ヘルパ）
- Spring Boot のプラグインは `:app*` 配下にのみ適用（現状、該当モジュールなし）。

## ビルドと静的解析
- Gradle Wrapper を使用します。
  - ビルド: `./gradlew build`
  - Lint（Detekt）: `./gradlew detekt`
- Detekt は `config/detekt/detekt.yml` を使用します。規約に準拠した小さく焦点の定まった変更を心がけてください。

## バージョンと依存管理
- 依存バージョンは Version Catalog を唯一の情報源とし、各モジュールの `build.gradle.kts` に直書きしないでください。
- Arrow は `libs.bundles.arrow`（中身は `arrow-core`, `arrow-fx-coroutines`）で参照できます。
- ULID 等のユーティリティは `libraries/fundamentals` 経由で提供されます。

## コーディング規約（要約）
- 詳細は `docs/coding_practice.md` に従うこと。
  - エンティティ: `AggregateRoot<ID>` を継承。`data class` + 不変プロパティ、必要に応じて `sealed class` で状態を表現し、遷移時にドメインイベントを発行。
  - VO: 可能な限り `@JvmInline value class`。`of(...)` で生成・検証し、プリミティブを直接露出しない。
  - リポジトリ: ドメイン側のポートは `Repository<E, ID>` を継承。API は原則 `suspend`、引数/戻り値は VO/ID を使用し、欠如は `Option` で表現。
  - ドメインエラー: 文脈ごとの `sealed interface` が `DomainError` を実装。例外ではなく `Either<Error, T>` を返す方針。

## 変更方針
- 変更は最小限にし、既存のスタイルと命名に合わせること。
- 無関係なリファクタを同一パッチに混在させないこと。
- ドメイン規約やシグネチャを変更する場合は、`docs/coding_practice.md` を更新すること。

## テスト
- テストを追加する場合は JUnit 5 を使用し、対象モジュール内に配置。VO や状態遷移のユニットテストを優先してください。

## 注意事項
- 依存解決にはネットワークアクセスが必要になる場合があります。Wrapper と Version Catalog を使用して整合性を保ってください。
- ライセンスヘッダは明示的な指示がない限り追加しないでください。
