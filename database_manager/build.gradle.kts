plugins {
	java
	id("io.freefair.lombok") version "9.2.0"
}

group = "dnd"
version = "0.0.1-SNAPSHOT"

repositories {
	mavenCentral()
}

java {
	sourceCompatibility = JavaVersion.VERSION_25
}



dependencies {
	compileOnly(libs.org.apache.tomcat.annotations.api)

	implementation(libs.org.springframework.boot.spring.boot.starter.jdbc)
	implementation(libs.org.postgresql.postgresql)

	testImplementation(libs.org.springframework.boot.spring.boot.starter.test)
}

tasks.test {
	useJUnitPlatform()
}
