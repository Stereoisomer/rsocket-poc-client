import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.6.3"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	kotlin("jvm") version "1.6.10"
	kotlin("plugin.spring") version "1.6.10"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
	mavenCentral()
}

dependencies {
	// RSocket
	implementation("org.springframework.boot:spring-boot-starter-rsocket")
	// Lambok
	compileOnly("org.projectlombok:lombok:1.18.22")
	// WebFlux
	implementation("org.springframework.boot:spring-boot-starter-webflux")
	// Reactor
	implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
	// Kotlin
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
	// Logging
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.13.+")
	implementation("io.github.microutils:kotlin-logging:2.1.21")
	implementation("ch.qos.logback:logback-classic")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("io.projectreactor:reactor-test")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "11"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
