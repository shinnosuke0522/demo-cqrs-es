plugins {
    alias(libs.plugins.kotlin.jvm)
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

    // Common config for all subprojects
    dependencies {
        implementation(libs.bundles.arrow)
        implementation(libs.ulid)
    }

    // Config for application module
    if (project.path.startsWith(":app")) {
        apply(plugin = libs.plugins.kotlin.jvm.get().pluginId)
        apply(plugin = libs.plugins.kotlin.spring.get().pluginId)
        apply(plugin = libs.plugins.spring.boot.get().pluginId)
        apply(plugin = libs.plugins.spring.dependency.management.get().pluginId)
    }

}