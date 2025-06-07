plugins {
    id("org.jetbrains.kotlin.plugin.compose")
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
    implementation(project(":core:model"))
    implementation("androidx.compose.material:material-icons-extended")
    implementation(platform("androidx.compose:compose-bom:${rootProject.extra["compose_bom_version"]}"))
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-tooling-preview")

    implementation("io.coil-kt:coil-compose:2.6.0")

    implementation("com.valentinilk.shimmer:compose-shimmer:1.2.0")
}
