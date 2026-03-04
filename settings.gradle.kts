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

rootProject.name = "network experience"
include(":app")

include(":com.example")
include(":DataBase.example.data")
include(":Jetpack.Learning.l")

project(":com.example").projectDir = file("com.example")
project(":DataBase.example.data").projectDir = file("DataBase.example.data")
project(":Jetpack.Learning.l").projectDir = file("Jetpack/Learning/l")