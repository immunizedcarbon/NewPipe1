plugins {
    id("com.android.library")
    kotlin("android")
}

android {
    compileSdk = 36
    namespace = "org.schabi.newpipe.core.domain"
    defaultConfig { minSdk = 21 }
    compileOptions { sourceCompatibility = JavaVersion.VERSION_17; targetCompatibility = JavaVersion.VERSION_17 }
    kotlinOptions { jvmTarget = "17" }
}

dependencies {
    implementation(project(":core:model"))
    implementation(project(":core:data"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.9.1")
}
