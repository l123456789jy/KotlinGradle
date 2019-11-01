import com.android.build.gradle.internal.api.ApkVariantOutputImpl
plugins  {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-android-extensions")
}
android {
    compileSdkVersion(28)
    defaultConfig {
        applicationId = "com.kotlin.dsl.gradle.sample"
        minSdkVersion(16)
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "android.support.test.runner.AndroidJUnitRunner"
    }

    signingConfigs {

    }

    buildTypes {
        getByName("release") {
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")

        }
        getByName("debug") {
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")

        }
    }

    packagingOptions {
        exclude("META-INF/LICENSE.txt")
        exclude("META-INF/NOTICE.txt")
    }

    android.applicationVariants.all {
        outputs.all {
            if (this is ApkVariantOutputImpl) {
                this.outputFileName = "$versionCode@app_$versionName.apk"
            }
        }
    }

}

task("greeting") {
    var age=10
    doLast { println(age) }
}


dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    val supportVersion = "28.0.0"
    implementation("com.android.support:appcompat-v7:$supportVersion")
    implementation("com.android.support:support-v4:$supportVersion")
    implementation("com.android.support:gridlayout-v7:$supportVersion")
    implementation("com.android.support:recyclerview-v7:$supportVersion")
    implementation("com.android.support:design:$supportVersion")

    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.2.61")

    testImplementation("junit:junit:4.12")
    androidTestImplementation("com.android.support.test:runner:1.0.2")
}