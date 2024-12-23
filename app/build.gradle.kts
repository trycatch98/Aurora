plugins {
    alias(libs.plugins.aurora.android.application)
    alias(libs.plugins.aurora.android.application.compose)
    alias(libs.plugins.aurora.hilt)
}

android {
    defaultConfig {
        applicationId = "com.trycatch.aurora"
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    namespace = "com.trycatch.aurora"
}

dependencies {
    implementation(projects.core.designsystem)
    implementation(projects.core.domain)
    implementation(projects.core.data)

    implementation(projects.feature.onboarding)
    implementation(projects.feature.importwallet)
    implementation(projects.feature.createwallet)
    implementation(projects.feature.home)
    implementation(projects.feature.setting)

    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.material3.adaptive)
    implementation(libs.androidx.compose.material3.adaptive.layout)
    implementation(libs.androidx.compose.material3.adaptive.navigation)
    implementation(libs.androidx.compose.material3.windowSizeClass)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.androidx.lifecycle.runtimeCompose)
    implementation(libs.androidx.navigation.compose)

    testImplementation(kotlin("test"))
    androidTestImplementation(kotlin("test"))
}