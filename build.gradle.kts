plugins {
    alias(libs.plugins.detekt.plugin) apply false
    alias(libs.plugins.kotlin.jvm) apply false
    alias(libs.plugins.kotlin.spring) apply false
    alias(libs.plugins.spring.boot) apply false
    alias(libs.plugins.spring.dependency.management) apply false
}

allprojects {
    repositories {
        mavenCentral()
        maven { url = uri("https://repo.spring.io/snapshot") }
    }
}

subprojects {
    group = "com.github.shinnosuke0522"
    version = "0.0.1-SNAPSHOT"

    // Version Catalog は rootProject から参照する
    val libs = rootProject.libs

    // common
    apply(plugin = libs.plugins.detekt.plugin.get().pluginId)
    apply(plugin = libs.plugins.kotlin.jvm.get().pluginId)

    dependencies {
        add("detektPlugins", libs.detekt.formatting)
    }

    // app module only（:app 配下すべて）
    if (path.startsWith(":app")) {
        apply(plugin = libs.plugins.kotlin.spring.get().pluginId)
        apply(plugin = libs.plugins.spring.boot.get().pluginId)
        apply(plugin = libs.plugins.spring.dependency.management.get().pluginId)
    }

    // task
    tasks.withType<io.gitlab.arturbosch.detekt.Detekt>().configureEach {
        // 並列処理
        parallel = true
        // 自動修正
        autoCorrect = true
        // Detektの関する設定ファイル
        config = files("$rootDir/config/detekt/detekt.yml")
        // デフォルト設定の上に自分の設定ファイルを適用する
        buildUponDefaultConfig = true
        // ルール違反があった場合にfailさせない
        // (正確にはルール違反の数がDetekt設定のmaxIssuesの値を超えたときにfailするのだが、
        //  デフォルト設定ではmaxIssuesは0になっているため、1つでもルール違反があるとfailする）
        ignoreFailures = true
        // レポートファイルに出力されるファイルパスのベースとなる
        // これが設定されてないとレポートファイルのパスは絶対パスになる
        basePath = rootDir.absolutePath + "/reports/detekt"
    }
}
