pluginManagement {
    repositories { gradlePluginPortal(); google(); mavenCentral() }
    plugins {
        id("com.android.application") version "8.6.0" apply false
        id("com.android.library")     version "8.6.0" apply false
        id("org.jetbrains.kotlin.android") version "2.0.0" apply false
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven("https://jitpack.io")
    }
}

include(":app")
include(":core:ui")
include(":core:data")
include(":core:model")
include(":core:domain")
include(":feature:player")
include(":feature:feed")
include(":feature:main")
include(":feature:history")

include(":feature:search")
include(":feature:subscriptions")
include(":feature:playlists")
include(":feature:settings")
include(":feature:channel")
include(":feature:playlist_detail")
include(":feature:downloads")
// Use a local copy of NewPipe Extractor by uncommenting the lines below.
// We assume, that NewPipe and NewPipe Extractor have the same parent directory.
// If this is not the case, please change the path in includeBuild().

//includeBuild('../NewPipeExtractor') {
//    dependencySubstitution {
//        substitute module('com.github.TeamNewPipe:NewPipeExtractor') using project(':extractor')
//    }
//}
