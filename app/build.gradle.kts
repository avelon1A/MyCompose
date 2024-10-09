plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.ksp)
    alias(libs.plugins.google.gms.google.services)
    alias(libs.plugins.google.firebase.crashlytics)
    id("kotlin-parcelize")
    id("org.jetbrains.kotlinx.kover") version "0.8.3"

}

android {
    namespace = "com.bosch.composewithkotlin20"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.bosch.composewithkotlin20"
        minSdk = 30
        targetSdk = 35
        versionCode = 1
        versionName = "1.0.1"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        create("dev") {
            applicationIdSuffix = ".Dev"
            isDebuggable = true
            isJniDebuggable = true
            resValue("string", "app_name", "Dev compose 2")
            signingConfig = signingConfigs.getByName("debug")
        }

    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }


    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    kotlin {

    }


}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.datastore.core.android)
    implementation(libs.androidx.media3.ui)
    implementation(libs.androidbrowserhelper)
    implementation(libs.androidx.runtime.livedata)
    implementation(libs.androidx.room.ktx)
    implementation(libs.androidx.room.common)
    implementation(libs.firebase.crashlytics)
    implementation(libs.androidx.ui.text.google.fonts)


    implementation(libs.navigation.compose)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.androidx.foundation)
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.coil)
    implementation(libs.coil.compose)
    implementation(libs.androidx.camera.viewfinder.compose)
    implementation(libs.ui)
    implementation(libs.ui.tooling.preview)
    implementation(libs.androidx.core.splashscreen)
    implementation(libs.androidx.datastore.preferences.core)
    implementation(libs.androidx.datastore.preferences)
    implementation(libs.exoplayer)
    implementation(libs.accompanist.permissions)
    implementation(libs.androidx.media3.exoplayer)
    implementation(libs.accompanist.systemuicontroller)
    implementation(libs.androidx.media3.session)
    implementation(libs.glide)
    implementation(libs.androidx.constraintlayout.compose)
    implementation(libs.androidx.material.icons.extended)
    implementation(libs.lottie.compose)
    implementation(libs.koin.android)
    implementation(libs.koin.android.compat)
    implementation(libs.koin.androidx.workmanager)
    implementation(libs.koin.androidx.navigation)
    implementation(libs.koin.core)
    implementation(libs.koin.androidx.compose)
    implementation(kotlin("test"))
    implementation(libs.androidx.animation.graphics.android)
    implementation(libs.androidx.foundation.android)
    testImplementation(libs.junit)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.mockito.core)
    testImplementation(libs.mockito.inline)
    testImplementation(libs.mockito.kotlin)
    testImplementation(libs.androidx.core.testing)
    implementation(libs.androidx.work.runtime)
    ksp(libs.androidx.room.compiler)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    implementation(platform("io.github.jan-tennert.supabase:bom:2.6.0"))
    implementation("io.github.jan-tennert.supabase:postgrest-kt:2.6.0")
    implementation("io.github.jan-tennert.supabase:gotrue-kt:2.6.0")
    implementation("io.github.jan-tennert.supabase:realtime-kt:2.6.0")

    implementation("io.ktor:ktor-client-cio:2.3.5")
    implementation("com.google.code.gson:gson:2.11.0")
    implementation("com.google.accompanist:accompanist-systemuicontroller:0.34.0")
    implementation("com.google.ai.client.generativeai:generativeai:0.7.0")
    implementation("org.java-websocket:Java-WebSocket:1.5.2")


}



