import java.util.*

plugins {
    kotlin("multiplatform") version "1.8.20"
    kotlin("plugin.serialization") version "1.8.20"
    id("com.android.library")
    id("org.jlleitschuh.gradle.ktlint") version "11.3.1"
    id("maven-publish")
    id("signing")
}

val local = Properties()
val localProperties: File = rootProject.file("local.properties")
if (localProperties.exists()) {
    localProperties.inputStream().use { local.load(it) }
}


val projectGithubUrl: String by project
val projectGithubSCM: String by project
val projectGithubSCMSSL: String by project
val projectDescription: String by project

val developerId: String by project
val developerName: String by project
val developerEmail: String by project
val group: String by project

val project_version: String by project
version = project_version

repositories {
    google()
    mavenCentral()
}

val ktor_version: String by project
val kotlinx_coroutines_version: String by project
val kermit_version: String by project

kotlin {

    android {
        publishLibraryVariants("release", "debug")
    }

    android {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }

    ios("ios") {
        binaries.framework {
            baseName = "jsonrpc"
        }
    }
    iosSimulatorArm64 {
        binaries.framework {
            baseName = "jsonrpc"
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-core:$ktor_version")
                implementation("io.ktor:ktor-serialization-kotlinx-json:$ktor_version")
                implementation("io.ktor:ktor-client-logging:$ktor_version")
                implementation("io.ktor:ktor-client-content-negotiation:$ktor_version")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$kotlinx_coroutines_version")
                implementation("co.touchlab:kermit:$kermit_version")
            }
        }
        val androidMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-android:$ktor_version")
            }
        }

        val iosMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-darwin:$ktor_version")
            }
        }
        val iosSimulatorArm64Main by getting
        iosSimulatorArm64Main.dependsOn(iosMain)
    }
}

android {
    compileSdk = 31
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = 24
        targetSdk = 31
    }
}

fun SigningExtension.whenRequired(block: () -> Boolean) {
    setRequired(block)
}

val javadocJar by tasks.creating(Jar::class) {
    archiveClassifier.value("javadoc")
}

publishing {
    repositories {
        maven {
            url = uri("https://maven.pkg.github.com/DenisDemyanko/jsonrpc-kotlin-client")

        }
    }

    publications.all {
        this as MavenPublication

        println(name)
        artifact(javadocJar)

        pom {
            name.set(group)
            description.set(projectDescription)
            url.set(projectGithubUrl)

            licenses {
                license {
                    name.set("MIT License")
                    url.set("http://opensource.org/licenses/MIT")
                }
            }

            developers {
                developer {
                    id.set(developerId)
                    name.set(developerName)
                    email.set(developerEmail)
                }
            }

            scm {
                url.set(projectGithubUrl)
                connection.set(projectGithubSCM)
                developerConnection.set(projectGithubSCMSSL)
            }
        }
    }
}

signing {
    whenRequired { gradle.taskGraph.hasTask("publish") }
    val signingKey: String? by project
    val signingPassword: String? by project
    useInMemoryPgpKeys(signingKey, signingPassword)
    sign(publishing.publications)
}

ktlint {
    version.set("0.41.0")
}

rootProject.plugins.withType<org.jetbrains.kotlin.gradle.targets.js.nodejs.NodeJsRootPlugin> {
    rootProject.the<org.jetbrains.kotlin.gradle.targets.js.nodejs.NodeJsRootExtension>().nodeVersion = "16.0.0"
}
