plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.dagger.hilt)
}

android {
    namespace = "de.kapschefsky.android.aviv.test.core.domain"
    compileSdk = 36

    defaultConfig {
        minSdk = 26

        consumerProguardFiles("consumer-rules.pro")
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
}

dependencies {
    listOf(
        libs.androidx.core.ktx,
        libs.androidx.appcompat,
        libs.arrow.core,
        libs.dagger.hilt.android,
    ).forEach(::implementation)

    listOf(
        "core:data",
        "core:model",
    ).forEach { moduleName ->
        implementation(project(":$moduleName"))
    }

    ksp(libs.dagger.hilt.compiler)
}