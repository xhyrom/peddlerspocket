import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    id("java")
    id("maven-publish")
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

group = "me.xhyrom.peddlerspocket"
version = "1.0.0"

repositories {
    mavenCentral()
    maven("https://repo.purpurmc.org/snapshots")
    maven("https://jitpack.io")
    maven("https://repo.codemc.org/repository/maven-public/")
}

dependencies {
    compileOnly("org.purpurmc.purpur", "purpur-api", "1.19.2-R0.1-SNAPSHOT")

    compileOnly("org.projectlombok:lombok:1.18.26")
    annotationProcessor("org.projectlombok:lombok:1.18.26")

    implementation("dev.jorel:commandapi-shade:8.8.0")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
}

tasks {
    named<Jar>("jar") {
        archiveClassifier.set("unshaded")
    }
    shadowJar {
        configureRelocations()
    }
    val shadowJarApi = register<ShadowJar>("shadowJarApi") {
        from(sourceSets.main.get().output)
        configurations = listOf(project.configurations.runtimeClasspath.get())
        archiveFileName.set("PeddlersPocket-"+project.version+".jar")

        configureRelocations()
    }
    named("build") {
        dependsOn(shadowJar)
        dependsOn(shadowJarApi)
    }
}

fun ShadowJar.configureRelocations() {
    relocate("dev.jorel.commandapi", "me.xhyrom.peddlerspocket.libs.commandapi")
}

publishing {
    publications.create<MavenPublication>("maven") {
        repositories.maven {
            url = uri("https://repo.jopga.me/releases")

            credentials(PasswordCredentials::class)
            authentication {
                create<BasicAuthentication>("basic")
            }
        }

        groupId = rootProject.group as String
        artifactId = project.name
        version = rootProject.version as String

        pom {
            name.set("PeddlersPocket")
        }
    }
}