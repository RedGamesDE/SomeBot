plugins {
    idea
    kotlin("jvm") version "1.9.0"
    id("com.bmuschko.docker-java-application") version "9.3.2"
}

group = "de.redgames"
version = "1.1"

repositories {
    mavenCentral()
    maven("https://m2.dv8tion.net/releases")
}

dependencies {
    implementation("net.dv8tion:JDA:5.0.0-beta.12")
}

kotlin {
    jvmToolchain(17)
}

docker {
    javaApplication {
        baseImage = "eclipse-temurin:17-jre"
        maintainer = "RedGames"
        ports = emptyList()
        images = setOf(
            "registry.redgames.de/somebot:$version",
            "registry.redgames.de/somebot:latest"
        )
    }

    registryCredentials {
        url = "https://registry.redgames.de/"
        username = providers.environmentVariable("REDGAMES_REGISTRY_USERNAME")
        password = providers.environmentVariable("REDGAMES_REGISTRY_PASSWORD")
    }
}

tasks {
    jar {
        manifest {
            attributes("Main-Class" to "de.redgames.somebot.Main")
        }
    }
}
