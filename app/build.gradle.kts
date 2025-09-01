plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.parcelize)
    alias(libs.plugins.ktlint)
    alias(libs.plugins.ksp)
    alias(libs.plugins.dagger.hilt)
    alias(libs.plugins.jetbrains.kotlin.serialization)
}

android {
    namespace = "de.kapschefsky.android.aviv.test"
    compileSdk = 36

    defaultConfig {
        applicationId = "de.kapschefsky.android.aviv.test"
        minSdk = 26
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        debug {
            isMinifyEnabled = false
        }

        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        @Suppress("DEPRECATION")
        jvmTarget = "11"
    }

    buildFeatures {
        compose = true
    }
}

dependencies {
    listOf(
        libs.androidx.compose.bom,
    ).forEach(::platform)

    listOf(
        libs.androidx.core.ktx,
        libs.androidx.lifecycle.runtime.ktx,
        libs.androidx.activity.compose,
        libs.androidx.ui,
        libs.androidx.ui.graphics,
        libs.androidx.ui.tooling.preview,
        libs.androidx.material3,
        libs.androidx.material3.windowsizeclass,
        libs.dagger.hilt.android,
        libs.kotlin.coroutines.android,
        libs.arrow.core,
        libs.androidx.adaptive.layout,
        libs.androidx.material3.navigation3,
        libs.androidx.navigation3.runtime,
        libs.androidx.navigation3.ui,
        libs.androidx.hilt.navigation.compose,
    ).forEach(::implementation)

    listOf(
        "core:domain",
        "core:model",
        "core:ui",
    ).forEach { moduleName ->
        implementation(project(":$moduleName"))
    }

    ksp(libs.dagger.hilt.compiler)

    listOf(
        libs.junit,
        libs.mockk,
        libs.kotlin.coroutines.test,
        libs.test.turbine,
    ).forEach(::testImplementation)

    listOf(
        libs.androidx.ui.test.manifest,
    ).forEach(::debugImplementation)

    androidTestImplementation(platform(libs.androidx.compose.bom))

    listOf(
        libs.androidx.junit,
        libs.androidx.ui.test.junit4,
    ).forEach(::androidTestImplementation)
}
