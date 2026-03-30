pluginManagement {
    repositories {
        /*google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }*/
        google()
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

rootProject.name = "network experience"
include(":app")

include(":com.example")
include(":data")
include(":Jetpack.Learning.l")

project(":com.example").projectDir = file("com.example")
project(":data").projectDir = file("data")
project(":Jetpack.Learning.l").projectDir = file("Jetpack/Learning/l")