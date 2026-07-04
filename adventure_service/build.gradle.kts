plugins {
    java
    id("org.springframework.boot") version "4.1.0"
    id("io.spring.dependency-management") version "1.1.7"
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
    implementation(libs.org.springframework.boot.spring.boot.starter.web)
    implementation(libs.org.springframework.boot.spring.boot.starter.data.jpa)
    implementation(libs.org.springframework.boot.spring.boot.starter.jdbc)
    implementation(libs.org.springdoc.openapi.starter.webmvc.ui)
    implementation(libs.org.postgresql.postgresql)

    implementation(libs.org.mapstruct.mapstruct)

    annotationProcessor(libs.org.mapstruct.mapstruct.processor)

    testImplementation(libs.org.springframework.boot.spring.boot.starter.test)
}

tasks.test {
    useJUnitPlatform()
}
