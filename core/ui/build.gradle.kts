plugins {
    id("org.jetbrains.kotlin.plugin.compose") version "2.1.21"
    id("com.android.library")
    kotlin("android")
}

android {
    compileSdk = 35
    namespace = "org.schabi.newpipe.core.ui"

    defaultConfig {
        minSdk = 21
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures { compose = true }
}

dependencies {
    implementation(platform("androidx.compose:compose-bom:${rootProject.extra["compose_bom_version"]}"))
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("io.coil-kt:coil-compose:2.7.0") // [Update 2025-06-06: von 2.6.0]
    implementation("com.valentinilk.shimmer:compose-shimmer:1.3.0") // [Update 2025-06-06: von 1.2.0]
}
