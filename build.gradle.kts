// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:8.4.0")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:2.1.21")
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.51")
        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle.kts files
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        maven("https://jitpack.io")
        maven("https://repo.clojars.org")
    }
    extra["kotlin_version"] = "2.1.21"
    extra["hilt_version"] = "2.51"
}
