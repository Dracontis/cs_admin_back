plugins {
    id("org.jetbrains.kotlin.jvm") version "1.3.70"
    id("org.jetbrains.kotlin.plugin.jpa") version "1.3.70"
    id("org.jetbrains.kotlin.plugin.spring") version "1.3.70"
    id("org.springframework.boot") version "2.2.5.RELEASE"
    id("io.spring.dependency-management") version "1.0.9.RELEASE"
}

repositories {
    mavenCentral()
}

dependencies {
    // Kotlin support
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    // Spring library
    implementation("org.springframework.boot:spring-boot-starter-web")
    // Database
    // TODO: flyway, spring data jpa
    // Tests
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.test {
    useJUnitPlatform {
        excludeEngines("junit-jupiter")
    }
}