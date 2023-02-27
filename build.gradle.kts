plugins {
    id("com.me.guanpj.version") apply false
}

buildscript {
    val kotlinVersion by extra("1.6.10")
    val android by extra("com.android.tools.build:gradle:7.4.1")
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath(android)
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.10")
        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
}

subprojects {
    // 子项目统一应用插件
    project.apply {
        apply(plugin = "com.me.guanpj.version")
        apply(plugin = "kotlin-android")
        apply(plugin = "kotlin-kapt")
    }
    if ("baselib" == project.name) project.apply(plugin = "com.android.library")
    else project.apply {
        apply(plugin = "com.android.application")
    }
}

tasks.register<Delete>("clean") {
    delete(buildDir)
}