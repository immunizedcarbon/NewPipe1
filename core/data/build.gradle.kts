plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
}

android {
    compileSdk = 36
    namespace = "org.schabi.newpipe.core.data"
    defaultConfig { minSdk = 21 }
    compileOptions { sourceCompatibility = JavaVersion.VERSION_17; targetCompatibility = JavaVersion.VERSION_17 }
    kotlinOptions { jvmTarget = "17" }
}

dependencies {
    implementation(project(":core:model"))
    implementation("androidx.room:room-runtime:2.7.1")
    kapt("androidx.room:room-compiler:2.7.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.9.1")
}
