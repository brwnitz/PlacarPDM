plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "com.example.placarpdm"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.placarpdm"
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
    kotlinOptions {
        jvmTarget = "1.8"
    }

    viewBinding.isEnabled = true
    viewBinding{
        enable = true
    }
}

val moxyVersion = "2.2.2"

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    //MOXY
    implementation ("com.github.moxy-community:moxy:$moxyVersion")
    androidTestImplementation(libs.junit.jupiter)
    annotationProcessor ("com.github.moxy-community:moxy-compiler:$moxyVersion")
    implementation ("com.github.moxy-community:moxy-android:$moxyVersion")
    implementation ("com.github.moxy-community:moxy-androidx:$moxyVersion")
}