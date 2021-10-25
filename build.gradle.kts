val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project

plugins {
    application
    kotlin("jvm") version "1.5.31"
}

group = "com.example"
version = "0.0.1"
application {
    mainClass.set("com.example.ApplicationKt")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.ktor:ktor-server-core:$ktor_version")
    implementation("io.ktor:ktor-auth:$ktor_version")
    implementation("io.ktor:ktor-auth-jwt:$ktor_version")
    implementation("io.ktor:ktor-server-tomcat:$ktor_version")
    implementation("ch.qos.logback:logback-classic:$logback_version")

    implementation("org.jetbrains.exposed", "exposed-core", "0.34.1")
    implementation("org.jetbrains.exposed", "exposed-dao", "0.34.1")
    implementation("org.jetbrains.exposed", "exposed-jdbc", "0.34.1")
    implementation("mysql:mysql-connector-java:8.0.25")
    implementation("io.ktor:ktor-jackson:$ktor_version")
    implementation("com.zaxxer:HikariCP:3.4.5")
    implementation("org.kodein.di:kodein-di-framework-ktor-server-jvm:7.0.0")
    testImplementation("io.ktor:ktor-server-tests:$ktor_version")
    testImplementation("org.jetbrains.kotlin:kotlin-test:$kotlin_version")
}