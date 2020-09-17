plugins {
    java
    application
}

repositories {
    jcenter()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter:5.6.2")
    testImplementation("org.assertj:assertj-core:3.17.1")
}

application {
    mainClassName = "com.github.SoGoesLight.holdem.TexasHoldem"
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

tasks.jar {
    manifest {
        attributes(
                "Main-Class" to "com.github.SoGoesLight.holdem.TexasHoldem"
        )
    }
}

val test by tasks.getting(Test::class) {
    useJUnitPlatform()
}
