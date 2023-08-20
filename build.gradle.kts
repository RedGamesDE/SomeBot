plugins {
    idea
    kotlin("jvm") version "1.9.0"
    id("com.bmuschko.docker-java-application") version "9.3.2"
}

group = "nexus.slime"
version = "1.2"

repositories {
    mavenCentral()
}

dependencies {
    implementation("net.dv8tion:JDA:5.0.0-beta.13")
}

kotlin {
    jvmToolchain(17)
}

docker {
    javaApplication {
        baseImage = "eclipse-temurin:17-jre"
        maintainer = "Slime Nexus"
        ports = emptyList()
        images = setOf(
            "registry.slime.nexus/somebot:$version",
            "registry.slime.nexus/somebot:latest"
        )
    }

    registryCredentials {
        url = "https://registry.slime.nexus/"
        username = providers.environmentVariable("SLIMENEXUS_REGISTRY_USERNAME")
        password = providers.environmentVariable("SLIMENEXUS_REGISTRY_PASSWORD")
    }
}

tasks {
    jar {
        manifest {
            attributes("Main-Class" to "nexus.slime.somebot.Main")
        }
    }
}
