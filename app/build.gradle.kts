import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.jetbrains.kotlin.plugin.compose") version "1.5.14"
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("kotlin-parcelize")
    id("com.google.dagger.hilt.android")
    id("org.sonarqube") version "4.0.0.2929"
}

android {
    compileSdk = 35
    namespace = "org.schabi.newpipe"

    defaultConfig {
        applicationId = "org.schabi.newpipe"
        minSdk = 21
        targetSdk = 35
        versionCode = 1004
        versionName = "0.27.7"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        javaCompileOptions {
            annotationProcessorOptions {
                arguments += mapOf(
                    "room.schemaLocation" to "$projectDir/schemas"
                )
            }
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        viewBinding = true
        buildConfig = true
        compose = true
    }


    packaging {
        resources {
            excludes += setOf(
                "META-INF/README.md",
                "META-INF/CHANGES",
                "META-INF/COPYRIGHT"
            )
        }
    }
}

dependencies {
    implementation("com.github.TeamNewPipe:nanojson:1.3")
    implementation("com.github.TeamNewPipe.NewPipeExtractor:NewPipeExtractor:v0.26.1")
    implementation("org.jetbrains.kotlin:kotlin-stdlib:${rootProject.extra["kotlin_version"]}")

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.preference:preference-ktx:1.2.1")
    // AppCompat
    // Alt: implementation("androidx.appcompat:appcompat:1.11.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.12.0") // [Update 2025-06-06: von 1.11.0]

    // Room (einheitlich auf 2.7.1 setzen)
    // Alt: implementation("androidx.room:room-runtime:2.7.1")
    implementation("androidx.room:room-runtime:2.6.1")
    implementation("androidx.room:room-ktx:2.6.1")
    kapt("androidx.room:room-compiler:2.6.1")

    implementation("com.google.dagger:hilt-android:${rootProject.extra["hilt_version"]}")
    kapt("com.google.dagger:hilt-compiler:${rootProject.extra["hilt_version"]}")

    implementation(platform("androidx.compose:compose-bom:${rootProject.extra["compose_bom_version"]}"))
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-tooling-preview")
    // Navigation Compose
    // Alt: implementation("androidx.navigation:navigation-compose:2.7.7")
    implementation("androidx.navigation:navigation-compose:2.7.7")
    // Hilt Navigation Compose
    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")
    implementation("com.nononsenseapps:filepicker:4.2.1") {
        exclude(group = "com.android.support", module = "support-compat")
    }

    implementation(project(":core:ui"))
    implementation(project(":core:domain"))
    implementation(project(":feature:player"))
    implementation(project(":feature:feed"))
    implementation(project(":feature:main"))
    implementation(project(":feature:search"))
    implementation(project(":feature:subscriptions"))
    implementation(project(":feature:settings"))
    implementation(project(":feature:downloads"))
}
