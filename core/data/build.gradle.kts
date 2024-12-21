plugins {
    alias(libs.plugins.aurora.android.library)
    alias(libs.plugins.aurora.hilt)
    id("kotlinx-serialization")
}

android {
    namespace = "com.trycatch.aurora.core.data"
}

dependencies {
    implementation(projects.core.domain)
}