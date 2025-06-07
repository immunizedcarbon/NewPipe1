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
        classpath("com.android.tools.build:gradle:8.4.0")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:2.0.0")
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.51.1")
        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle.kts files
    }
}


allprojects {
    extra["kotlin_version"] = "2.0.0"
    extra["hilt_version"] = "2.51.1"
    extra["compose_bom_version"] = "2024.05.00"
}

subprojects {
    plugins.withId("org.jetbrains.kotlin.jvm") {
        apply(plugin = "io.gitlab.arturbosch.detekt")
    }
    plugins.withId("org.jetbrains.kotlin.android") {
        apply(plugin = "io.gitlab.arturbosch.detekt")
    }
}

subprojects {
    configurations.all {
        exclude(group = "com.android.support", module = "support-compat")
    }
}
