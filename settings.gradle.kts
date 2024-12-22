pluginManagement {
    includeBuild("build-logic")
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
}

rootProject.name = "Aurora"

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
include(":app")

// core
include(":core:designsystem")
include(":core:domain")
include(":core:data")

// feature
include(":feature:onboarding")
include(":feature:importwallet")
include(":feature:createwallet")
include(":feature:home")
include(":feature:setting")
