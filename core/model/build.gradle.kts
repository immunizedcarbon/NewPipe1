plugins {
    id("com.android.library")
    kotlin("android")
}

android {
    compileSdk = 35
    namespace = "org.schabi.newpipe.core.model"
    defaultConfig { minSdk = 21 }
    compileOptions { sourceCompatibility = JavaVersion.VERSION_17; targetCompatibility = JavaVersion.VERSION_17 }
    kotlinOptions { jvmTarget = "17" }
}

dependencies {}
