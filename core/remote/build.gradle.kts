import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    alias(libs.plugins.aurora.android.library)
    alias(libs.plugins.aurora.hilt)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.trycatch.aurora.core.remote"
    buildFeatures.buildConfig = true

    defaultConfig {
        buildConfigField("String", "CMM_API_KEY", getProperty("CMM_API_KEY"))
    }
}

fun getProperty(propertyKey: String): String {
    return gradleLocalProperties(rootDir, providers).getProperty(propertyKey)
}

dependencies {
    implementation(projects.core.data)
    implementation(projects.core.crypto)

    implementation(libs.retrofit.core)
    implementation(libs.retrofit.kotlin.serialization)
    implementation(libs.okhttp)
    implementation(libs.okhttp.logging)

    implementation(libs.kotlinx.serialization.json)
}
