import com.vanniktech.maven.publish.SonatypeHost
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.vanniktech.mavenPublish)
    alias(libs.plugins.kotlinx.serialization)
    alias(libs.plugins.cocoapods)
}

group = "io.github.shangeethsivan.kmplibrarysample"
version = "0.0.3"

kotlin {
    jvm()
    androidTarget {
        publishLibraryVariants("release")
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_1_8)
        }
    }
    // IOS Simulators
    iosX64()
    // New IOS Devices
    iosArm64 {
        binaries {
            framework {
                baseName = "SharedLibrary"
            }
        }
    }
    // Simulators
    iosSimulatorArm64()
    linuxX64()

    sourceSets {
        val commonMain by getting {
            dependencies {
                //put your multiplatform dependencies here
                implementation(project.dependencies.platform(libs.koin.bom))
                implementation(libs.koin.core)
                implementation(libs.ktor.client.core)
                implementation(libs.ktor.client.content.negotiation)
                implementation(libs.ktor.client.json)
                implementation(libs.kotlin.serial.json)
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(libs.ktor.client.okhttp)
            }
        }
        val iosX64Main by getting {
            dependencies {
                implementation(libs.ktor.client.darwin)
            }
        }
        val iosArm64Main by getting {
            dependencies {
                implementation(libs.ktor.client.darwin)
            }
        }
        val iosSimulatorArm64Main by getting {
            dependencies {
                implementation(libs.ktor.client.darwin)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(libs.kotlin.test)
            }
        }
    }

    cocoapods {
        name = "SharedLibrary"
        version = "0.0.3"
        summary = "A Test Kotlin Multiplatform shared library"
        homepage = "https://github.com/shangeethsivan/kmplibrarysample"

        ios.deploymentTarget = "16.0"

        framework {
            baseName = "SharedLibrary"
        }
    }
}

android {
    namespace = "io.github.shangeethsivan.kmplibrarysample"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
}

mavenPublishing {
    publishToMavenCentral(SonatypeHost.CENTRAL_PORTAL)

    signAllPublications()

    coordinates(group.toString(), "library", version.toString())

    pom {
        name = "KMP Sample Library"
        description = "A sample KMP Library that has network layer and repo layer"
        inceptionYear = "2024"
        url = "https://github.com/shangeethsivan/kmplibrarysample"
        licenses {
            license {
                name = "The Apache License, Version 2.0"
                url = "https://www.apache.org/licenses/LICENSE-2.0.txt"
                distribution = "https://www.apache.org/licenses/LICENSE-2.0.txt"
            }
        }
        developers {
            developer {
                id = "shangeethsivan"
                name = "Shangeeth Sivan"
                url = "https://github.com/shangeethsivan/"
            }
        }
        scm {
            url = "https://github.com/shangeethsivan/kmplibrarysample/"
            connection = "scm:git:git://github.com/shangeethsivan/kmplibrarysample.git"
            developerConnection = "scm:git:ssh://git@github.com/shangeethsivan/kmplibrarysample.git"
        }
    }
}
