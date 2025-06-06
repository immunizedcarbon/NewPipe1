// Top-level build file where you can add configuration options common to all sub-projects/modules.

plugins {
    id("io.gitlab.arturbosch.detekt") version "1.23.8" apply false // [Update 2025-06-06: von 1.24.0]
}

buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:8.10.1") // [Update 2025-06-06: von 8.2.2]
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:2.1.20") // [Update 2025-06-06: von 2.1.21]
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.56.2") // [Update 2025-06-06: von 2.48]
        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle.kts files
    }
}


allprojects {
    extra["kotlin_version"] = "2.1.20" // [Update 2025-06-06: von 2.1.21]
    extra["hilt_version"] = "2.56.2" // [Update 2025-06-06: von 2.48]
    extra["compose_bom_version"] = "2025.05.00" // [Update 2025-06-06: von 2024.02.02]
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
