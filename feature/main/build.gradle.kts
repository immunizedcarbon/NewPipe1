plugins {
    id("org.jetbrains.kotlin.plugin.compose") version "2.1.21"
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
}

android {
    compileSdk = 36
    namespace = "org.schabi.newpipe.feature.main"
    defaultConfig { minSdk = 21 }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions { jvmTarget = "17" }
    buildFeatures { compose = true }
    composeOptions {
        kotlinCompilerExtensionVersion = rootProject.extra["compose_compiler_version"] as String
    }
}

dependencies {
    implementation(project(":core:ui"))
    implementation(project(":core:domain"))
    implementation(project(":feature:feed"))
    implementation(project(":feature:history"))
    implementation(project(":feature:subscriptions"))
    implementation(project(":feature:playlists"))
    implementation(project(":feature:channel"))
    implementation(project(":feature:playlist_detail"))
    implementation(platform("androidx.compose:compose-bom:${rootProject.extra["compose_bom_version"]}"))
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.navigation:navigation-compose:2.7.7")
    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")
    implementation("com.google.dagger:hilt-android:${rootProject.extra["hilt_version"]}")
    kapt("com.google.dagger:hilt-compiler:${rootProject.extra["hilt_version"]}")
}
