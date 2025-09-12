pluginManagement {
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
    }
}

rootProject.name = "BasicNavigationCompose"
include(":app")
include(":core")
include(":feature")
include(":feature:foryou")
include(":feature:search")
include(":feature:library")
include(":core:data")
include(":core:ui")
include(":feature:detailpage")
include(":core:utils")
