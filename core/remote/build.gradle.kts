plugins {
    alias(libs.plugins.aurora.android.library)
    alias(libs.plugins.aurora.hilt)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.trycatch.aurora.core.remote"
}

dependencies {
    implementation(projects.core.data)

    implementation(libs.retrofit.core)
    implementation(libs.retrofit.kotlin.serialization)
    implementation(libs.okhttp)
    implementation(libs.okhttp.logging)

    // json parsing
    implementation(libs.kotlinx.serialization.json)
}