plugins {
    alias(libs.plugins.aurora.android.library)
    alias(libs.plugins.aurora.hilt)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.trycatch.aurora.core.crypto"
}

dependencies {
    implementation(projects.core.domain)
    implementation(projects.core.data)
    implementation(libs.retrofit.core)
    implementation(libs.retrofit.kotlin.serialization)
    implementation(libs.okhttp)
    implementation(libs.okhttp.logging)

    implementation(libs.solanakt)
    implementation(libs.metaplex) {
        exclude(group = "com.github.metaplex-foundation", module = "SolanaKT")
        exclude(group = "com.github.metaplex-foundation.kborsh", module = "kborsh-jvm")
    }

    // json parsing
    implementation(libs.kotlinx.serialization.json)
}