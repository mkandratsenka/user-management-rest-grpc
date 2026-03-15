plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.spring)
    alias(libs.plugins.spring.boot)
    alias(libs.plugins.spring.dependency.management)
}

kotlin {
    jvmToolchain(21)
}

dependencies {
    implementation(project(":proto"))
    implementation(libs.spring.boot.starter)

    implementation(libs.grpc.server.spring.boot.starter)
    implementation(libs.grpc.kotlin.stub)
    implementation(libs.protobuf.kotlin)

    implementation(libs.kotlinx.coroutines.core)
}