plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.hilt.kotlin.android)
    id("kotlin-kapt")
}

android {
    namespace = "com.mnaik.roomdb"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.mnaik.roomdb"
        minSdk = 24
        targetSdk = 34
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildFeatures {
        dataBinding = true
        viewBinding = true
        buildConfig = true
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.room)
    kapt(libs.androidx.room.compiler)
    implementation(libs.hilt)
    kapt(libs.hilt.compiler)
    implementation(libs.ui.sdp)
    implementation(libs.ui.ssp)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")


    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}