plugins {
	id("org.springframework.boot") version "3.0.6"
	id("io.spring.dependency-management") version "1.1.0"
	java
}

repositories {
	mavenCentral()
}

val springCloudVersion = "2022.0.0"

dependencies {
	implementation(platform("org.springframework.cloud:spring-cloud-dependencies:${springCloudVersion}"))

	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-jdbc")
	implementation("org.liquibase:liquibase-core")

	implementation("org.postgresql:postgresql")

	implementation("org.springframework.cloud:spring-cloud-stream-binder-kafka-streams")
	implementation("org.springframework.cloud:spring-cloud-stream")
	implementation("org.springframework.cloud:spring-cloud-starter-stream-kafka")

	// jwt
	implementation("io.jsonwebtoken:jjwt-api:0.11.5")
	implementation("javax.xml.bind:jaxb-api:2.3.1")
	runtimeOnly("io.jsonwebtoken:jjwt-impl:0.11.5")
	runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.11.5")

	//LOMBOK Dependencies
	annotationProcessor("org.projectlombok:lombok")
	compileOnly("org.projectlombok:lombok")
	testAnnotationProcessor("org.projectlombok:lombok")
	testCompileOnly("org.projectlombok:lombok")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("com.h2database:h2")
}

tasks {
	test {
		useJUnitPlatform()
	}
}