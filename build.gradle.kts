plugins {
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
    apply(plugin = libs.plugins.kotlin.jvm.get().pluginId)

    // app module only（:app 配下すべて）
    if (path.startsWith(":app")) {
        apply(plugin = libs.plugins.kotlin.spring.get().pluginId)
        apply(plugin = libs.plugins.spring.boot.get().pluginId)
        apply(plugin = libs.plugins.spring.dependency.management.get().pluginId)
    }
}
