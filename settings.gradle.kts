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
include(
    //app
    ":app",

    // core
    ":core:designsystem",
    ":core:domain",
    ":core:data",
    ":core:crypto",
    ":core:local",
    ":core:remote",

    // feature
    ":feature:onboarding",
    ":feature:importwallet",
    ":feature:createwallet",
    ":feature:home",
    ":feature:setting"
)
