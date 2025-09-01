plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "de.kapschefsky.android.aviv.test.core.ui"
    compileSdk = 36

    defaultConfig {
        minSdk = 26

        consumerProguardFiles("consumer-rules.pro")

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
                "proguard-rules.pro"
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
    implementation(platform(libs.androidx.compose.bom))

    listOf(
        libs.androidx.core.ktx,
        libs.androidx.appcompat,
        libs.androidx.material3,
        libs.androidx.ui.tooling.preview,
        libs.coil.network,
    ).forEach(::implementation)

    listOf(
        libs.androidx.material.icons.extended,
        libs.coil.compose,
    ).forEach(::api)

    listOf(
        libs.androidx.ui.test.manifest,
    ).forEach(::debugImplementation)

    androidTestImplementation(platform(libs.androidx.compose.bom))

    listOf(
        libs.androidx.junit,
        libs.androidx.ui.test.junit4
    ).forEach(::androidTestImplementation)
}