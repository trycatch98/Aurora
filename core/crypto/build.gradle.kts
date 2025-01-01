import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    alias(libs.plugins.aurora.android.library)
    alias(libs.plugins.aurora.hilt)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.trycatch.aurora.core.crypto"
    buildFeatures.buildConfig = true

    buildTypes {
        debug {
            buildConfigField("String", "SOLANA_RPC_URL", getProperty("SOLANA_TESTNET"))
        }
        release {
            buildConfigField("String", "SOLANA_RPC_URL", getProperty("SOLANA_MAINNET"))
        }
    }
}

fun getProperty(propertyKey: String): String {
    return gradleLocalProperties(rootDir, providers).getProperty(propertyKey)
}

dependencies {
    implementation(projects.core.domain)
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