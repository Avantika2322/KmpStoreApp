import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.plugin.mpp.apple.XCFramework

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.native.coroutines)
}

kotlin {
    val xcf = XCFramework("Shared")
    androidTarget {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    listOf(
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "Shared"
            xcf.add(this)
            export(project(":core-network"))
            export(project(":features:search:data"))
            export(project(":features:search:domain"))
            export(project(":features:search:presentation"))
            export(project(":features:details:data"))
            export(project(":features:details:domain"))
            export(project(":features:details:presentation"))
            isStatic = true
        }
    }

    sourceSets {
        all {
            languageSettings.optIn("kotlinx.cinterop.ExperimentalForeignApi")
        }
        commonMain.dependencies {
            api(projects.coreNetwork)
            api(projects.features.search.data)
            api(projects.features.search.domain)
            api(projects.features.search.presentation)
            api(projects.features.details.presentation)
            api(projects.features.details.domain)
            api(projects.features.details.data)
            implementation(libs.koin.core)
            api(libs.kmp.observableviewmodel.core)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

android {
    namespace = "com.kmpappdemo.app.shared"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
}
