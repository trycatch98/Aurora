plugins {
    alias(libs.plugins.aurora.android.library)
    id("com.google.devtools.ksp")
    alias(libs.plugins.aurora.hilt)
}

android {
    namespace = "com.trycatch.aurora.core.domain"
}