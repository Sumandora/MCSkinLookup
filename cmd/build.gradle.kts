plugins {
    kotlin("jvm") version "1.9.0"
    kotlin("plugin.serialization") version "1.9.0"
    application
}

base {
    java.toolchain.languageVersion = JavaLanguageVersion.of(8)
}

group = "su.mandora"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":"))
}
tasks.withType<JavaCompile> {
    options.release = 8
}

java {
    targetCompatibility = JavaVersion.VERSION_1_8
    sourceCompatibility = JavaVersion.VERSION_1_8
}

kotlin {
    jvmToolchain(8)
}

application {
    mainClass.set("MainKt")
}