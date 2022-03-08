plugins {
	java
	kotlin("jvm") version "1.6.10"
	id("org.springframework.boot") version "2.4.4"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	id("org.jetbrains.kotlin.plugin.spring") version "1.6.10"
	id("org.jetbrains.kotlin.plugin.jpa") version "1.6.10"
}

group = "cl.assachile"
version = "1.1.0-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_15

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

sourceSets {
	main {
		java.setSrcDirs(listOf("src/main/kotlin"))
	}
}

repositories {
	mavenCentral()
}

springBoot {
	mainClass.set("cl.assachile.productWeightHandler.ProductWeightHandlerApplicationKt")
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-jdbc")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.postgresql:postgresql:42.3.3")
	annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
	testImplementation("org.springframework.boot:spring-boot-starter-test")

	//APACHE POI -> EXCEL FILES
	implementation("org.apache.poi:poi:5.1.0")
	implementation("org.apache.poi:poi-ooxml:5.1.0")

	//JAVAMAIL
	implementation("com.sun.mail:javax.mail:1.6.2")

	//logger
	runtimeOnly("org.apache.logging.log4j:log4j-core:2.17.0")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
