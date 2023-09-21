plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    // Add the Google services Gradle plugin
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.firebasestorageexample"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.example.firebasestorageexample"
        minSdk = 24
        targetSdk = 33
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
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")


    //sdp
    implementation("com.intuit.sdp:sdp-android:1.1.0")

    //run time permission
    implementation("com.karumi:dexter:6.2.3")

    //load images
    implementation("com.github.bumptech.glide:glide:4.15.1")

    //fonts
    implementation("io.github.inflationx:calligraphy3:3.1.1")
    implementation("io.github.inflationx:viewpump:2.0.3")

    //ViewPager
    implementation("androidx.viewpager2:viewpager2:1.0.0")

    //Firebase lib
    implementation(platform("com.google.firebase:firebase-bom:32.2.2"))
    implementation("com.google.firebase:firebase-analytics-ktx")
    implementation("com.google.firebase:firebase-database-ktx")
    implementation("com.firebaseui:firebase-ui-storage:7.2.0")
    implementation("com.google.firebase:firebase-storage-ktx:20.2.1")
    //Firebase lib

}