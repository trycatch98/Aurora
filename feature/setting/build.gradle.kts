plugins {
    alias(libs.plugins.aurora.android.feature)
    alias(libs.plugins.aurora.android.library.compose)
}

android {
    namespace = "com.trycatch.aurora.feature.setting"
}

dependencies {
    implementation(projects.core.domain)
}