// Top-level build file where you can add configuration options common to all sub-projects/modules.

plugins {
    id("io.gitlab.arturbosch.detekt") version "1.23.6" apply false
}

buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:8.2.2")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:2.1.21")
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.48")
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
    extra["hilt_version"] = "2.48"
    extra["compose_compiler_version"] = "1.5.10"
    extra["compose_bom_version"] = "2024.02.02"
}

subprojects {
    plugins.withId("org.jetbrains.kotlin.jvm") {
        apply(plugin = "io.gitlab.arturbosch.detekt")
    }
    plugins.withId("org.jetbrains.kotlin.android") {
        apply(plugin = "io.gitlab.arturbosch.detekt")
    }
}
