plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")

    id("androidx.navigation.safeargs.kotlin")
}

android {
    namespace = "com.study.typeui"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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

    buildFeatures{
        viewBinding = true
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")

    //Navigation
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.7")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.7")

    //Gson
    implementation ("com.google.code.gson:gson:2.9.0")

    //Glide(이미지)
    implementation ("com.github.bumptech.glide:glide:4.12.0")
    annotationProcessor ("com.github.bumptech.glide:compiler:4.12.0")

    //cardView
    implementation ("androidx.cardview:cardview:1.0.0")

    //viewPager
    implementation ("androidx.viewpager2:viewpager2:1.1.0")

    //recyclerview
    implementation ("androidx.recyclerview:recyclerview:1.3.2")

    //KTX 종속성(수명 주기 인식 구성요소 코루틴 사용)
    //ViewModelScope
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.4")
    //LifecycleScope
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.4")
    //liveData
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.8.4")
}