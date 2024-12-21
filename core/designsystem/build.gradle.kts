plugins {
    alias(libs.plugins.aurora.android.library)
    alias(libs.plugins.aurora.android.library.compose)
}

android {
    namespace = "com.trycatch.aurora.designsystem"
}

dependencies {
    api(libs.androidx.compose.foundation)
    api(libs.androidx.compose.foundation.layout)
    api(libs.androidx.compose.material.iconsExtended)
    api(libs.androidx.compose.material3)
    api(libs.androidx.compose.material3.adaptive)
    api(libs.androidx.compose.material3.navigationSuite)
    api(libs.androidx.compose.runtime)
    api(libs.androidx.compose.ui.util)
    api(libs.coil.kt.compose)
}