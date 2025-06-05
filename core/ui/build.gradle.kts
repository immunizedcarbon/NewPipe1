plugins {
    id("com.android.library")
    kotlin("android")
}

android {
    compileSdk = 36
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
}

dependencies {
    implementation("androidx.compose.material3:material3:1.2.1")
}
