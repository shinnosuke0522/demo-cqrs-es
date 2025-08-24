rootProject.name = "demo-cqrs-es"

pluginManagement {
    repositories {
        maven { url = uri("https://repo.spring.io/snapshot") }
        gradlePluginPortal()
    }
}
include(":domain")
include(":libraries:fundamentals")