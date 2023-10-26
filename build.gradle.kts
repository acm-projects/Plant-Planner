// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:8.1.2")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.20-RC")
        classpath("androidx.navigation:navigation-safe-args-generator:2.7.4")
    }
}

allprojects {
    repositories {
        google()
        jcenter()
    }
}

tasks.register(name = "type", type = Delete::class) {
    delete(rootProject.buildDir)
}