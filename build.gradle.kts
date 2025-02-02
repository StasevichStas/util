//import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
//
//plugins {
//    java
//    application
//}
//
//group = "com.example"
//version = ""
//
//repositories {
//    mavenCentral()
//}
//
//dependencies {
//    implementation("com.google.guava:guava:31.0.1-jre")
//    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
//    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
//    implementation("org.slf4j:slf4j-api:2.0.7")
//    implementation("ch.qos.logback:logback-classic:1.4.11")
////    compileOnly ("org.projectlombok:lombok")
////    annotationProcessor ("org.projectlombok:lombok")
////    implementation("org.slf4j:slf4j-api:1.7.36")
////    runtimeOnly("ch.qos.logback:logback-classic:1.2.11")
////    compileOnly("org.projectlombok:lombok:1.18.24")
////    annotationProcessor("org.projectlombok:lombok:1.18.24")
//    compileOnly("org.projectlombok:lombok:1.18.30") // Проверьте актуальную версию
//    annotationProcessor("org.projectlombok:lombok:1.18.30")
//
//    // Другие зависимости, если они есть
//    //implementation("org.slf4j:slf4j-api:2.0.7")
//}
////tasks.withType<JavaCompile> {
////    options.compilerArgs.add("-Xlint:unchecked")
////    options.compilerArgs.add("-Xlint:deprecation")
////}
////application {
////    mainClass.set("com.example.com.example.Main")
////}
//
//tasks.test {
//    useJUnitPlatform()
//}
//
//java {
//    toolchain {
//        languageVersion.set(JavaLanguageVersion.of(21))
//    }
//}
//
//tasks.jar {
//    manifest {
//        attributes["Main-Class"] = "com.example.Main"
//    }
//}


plugins {
    java
    application
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "com.example"
version = ""

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.google.guava:guava:31.0.1-jre")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
    implementation("org.slf4j:slf4j-api:2.0.7")
    implementation("ch.qos.logback:logback-classic:1.4.11")
    compileOnly("org.projectlombok:lombok:1.18.30")
    annotationProcessor("org.projectlombok:lombok:1.18.30")
    implementation("commons-cli:commons-cli:1.5.0")
}
application {
    mainClass.set("com.example.Main")
}
tasks.test {
    useJUnitPlatform()
}
java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}
tasks.jar {
    enabled = false // Отключает создание стандартного JAR-файла
}
tasks.shadowJar {
    archiveClassifier.set("")
    archiveBaseName.set("util") // Указываем базовое имя файла
    mergeServiceFiles()

    manifest {
        attributes["Main-Class"] = "com.example.Main"
    }

}
tasks.startScripts {
    dependsOn(tasks.shadowJar)
}