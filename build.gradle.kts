plugins {
    application
    kotlin("jvm") version "2.1.0"
    kotlin("plugin.serialization") version "2.1.0"
}

application {
    mainClass.set("aoc.util.Runner")
}

version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("org.reflections", "reflections", "0.10.2")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.3")
    implementation("org.slf4j:slf4j-nop:2.0.16")

    testImplementation("org.junit.jupiter", "junit-jupiter-api", "5.11.3")
    testImplementation("org.junit.jupiter", "junit-jupiter-engine", "5.11.3")
    testImplementation("io.kotest", "kotest-runner-junit5", "5.9.1")
    testImplementation("com.github.stefanbirkner:system-lambda:1.2.1")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
