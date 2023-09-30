plugins {
    kotlin("jvm") version "1.9.0"
    kotlin("plugin.serialization") version "1.9.0"
    `maven-publish`
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
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")
    testImplementation(kotlin("test"))
}

tasks {
    test {
        useJUnitPlatform()
    }

    withType<JavaCompile> {
        options.release = 8
    }
}

java {
    targetCompatibility = JavaVersion.VERSION_1_8
    sourceCompatibility = JavaVersion.VERSION_1_8
}

kotlin {
    jvmToolchain(8)
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            groupId = project.group as String
            artifactId = rootProject.name
            version = project.version as String

            from(components["java"])

            pom {
                name.set(rootProject.name)
                description.set("Simple library to lookup skin textures")
                url.set("https://github.com/Sumandora/MCSkinLookup")

                licenses {
                    license {
                        name.set("MIT License")
                        url.set("https://raw.githubusercontent.com/Sumandora/MCSkinLookup/master/LICENSE")
                    }
                }
            }
        }
    }
}