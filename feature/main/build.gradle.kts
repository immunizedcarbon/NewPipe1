plugins {
    id("com.android.library")
    kotlin("android")
}

android {
    compileSdk = 36
    namespace = "org.schabi.newpipe.feature.main"
    defaultConfig { minSdk = 21 }
    compileOptions { sourceCompatibility = JavaVersion.VERSION_17; targetCompatibility = JavaVersion.VERSION_17 }
    kotlinOptions { jvmTarget = "17" }
}

dependencies {
    implementation(project(":core:ui"))
    implementation(project(":core:domain"))
}
