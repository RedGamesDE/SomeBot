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
        baseImage.set("eclipse-temurin:17-jre")
        maintainer.set("RedGames")
        ports.set(emptyList())
        images.set(setOf(
            "registry.redgames.de/somebot:$version",
            "registry.redgames.de/somebot:latest"
        ))
    }

    registryCredentials {
        url.set("https://registry.redgames.de/")
        username.set(providers.environmentVariable("REDGAMES_REGISTRY_USERNAME"))
        password.set(providers.environmentVariable("REDGAMES_REGISTRY_PASSWORD"))
    }
}

tasks {
    jar {
        manifest {
            attributes("Main-Class" to "de.redgames.somebot.Main")
        }
    }
}
