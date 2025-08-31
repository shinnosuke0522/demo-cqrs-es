rootProject.name = "demo-cqrs-es"

pluginManagement {
    repositories {
        maven { url = uri("https://repo.spring.io/snapshot") }
        gradlePluginPortal()
    }
}
//include(":domain")
include(":domain:domain-base")
include(":domain:conference-domain")
include(":domain:organization-domain")
include(":domain:user-domain")
include(":libraries:fundamentals")