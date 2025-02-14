plugins {
    id("java")
}

group = "org.employees.report.utility"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    // https://mvnrepository.com/artifact/info.picocli/picocli
    implementation("info.picocli:picocli:4.7.6")

}

tasks.jar{
    manifest{
        attributes["Main-Class"] = "org.employees.report.utility.Main"
    }
    from(configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) })
}