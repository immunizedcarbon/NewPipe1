plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
}

android {
    compileSdk = 35
    namespace = "org.schabi.newpipe.core.data"
    defaultConfig { minSdk = 21 }
    compileOptions { sourceCompatibility = JavaVersion.VERSION_17; targetCompatibility = JavaVersion.VERSION_17 }
    kotlinOptions { jvmTarget = "17" }
}

dependencies {
    implementation(project(":core:model"))
    implementation("androidx.room:room-runtime:2.7.1") // [Update 2025-06-06: von 2.6.1]
    implementation("androidx.room:room-ktx:2.7.1") // [Update 2025-06-06]
    kapt("androidx.room:room-compiler:2.7.1") // [Update 2025-06-06: von 2.6.1]
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.10.2") // [Update 2025-06-06: von 1.10.2]
    implementation("com.github.TeamNewPipe.NewPipeExtractor:NewPipeExtractor:v0.24.6")
    implementation("androidx.datastore:datastore-preferences:1.2.0-alpha02") // [Update 2025-06-06: von 1.1.0]
    implementation("com.google.dagger:hilt-android:${rootProject.extra["hilt_version"]}")
    kapt("com.google.dagger:hilt-compiler:${rootProject.extra["hilt_version"]}")
}
