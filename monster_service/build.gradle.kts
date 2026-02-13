
plugins {
    java
    `maven-publish`
    id("io.freefair.lombok") version "9.2.0"

}

repositories {
    mavenLocal()
    maven {
        url = uri("https://repo.maven.apache.org/maven2/")
    }
}

dependencies {
    compileOnly(libs.org.apache.tomcat.annotations.api)

    runtimeOnly(libs.org.postgresql.postgresql)

    implementation(libs.org.springframework.boot.spring.boot.starter.data.jpa)
    implementation(libs.org.springframework.boot.spring.boot.starter)
    implementation(libs.org.springframework.kafka.spring.kafka)
    implementation(libs.com.fasterxml.jackson.datatype.jackson.datatype.jsr310)
    implementation(libs.org.apache.commons.commons.math3)
    implementation(libs.org.springframework.boot.spring.boot.starter.web)
    implementation(project(":shared"))

    testImplementation(libs.org.springframework.boot.spring.boot.starter.test)

    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

group = "dnd"
version = "0.0.1-SNAPSHOT"
description = "RestApi"
java.sourceCompatibility = JavaVersion.VERSION_25

publishing {
    publications.create<MavenPublication>("maven") {
        from(components["java"])
    }
}

tasks.withType<JavaCompile>() {
    options.encoding = "UTF-8"
}

tasks.withType<Javadoc>() {
    options.encoding = "UTF-8"
}

tasks.test {
    useJUnitPlatform()
}