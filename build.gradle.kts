import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.2.1.RELEASE"
	id("io.spring.dependency-management") version "1.0.8.RELEASE"
	kotlin("jvm") version "1.3.50"
	kotlin("plugin.spring") version "1.3.50"
	kotlin("plugin.jpa") version "1.3.50"

	id("com.bmuschko.docker-spring-boot-application") version "6.0.0"
}

group = "xyz.treelar"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8

repositories {
	mavenCentral()
	jcenter()
	gradlePluginPortal()
}

docker {
	springBootApplication {
		baseImage.set("openjdk:8-alpine")
		jvmArgs.set(listOf("-Dspring.profiles.active=production", "-Xmx2048m"))
		images.set(listOf("ninjawarrior1337/hanamaru/hanamaru:latest"))
	}
}

val developmentOnly by configurations.creating
configurations {
	runtimeClasspath {
		extendsFrom(developmentOnly)
	}
}

dependencies {
	implementation("net.dv8tion:JDA:4.0.0_39")
	implementation("com.jagrosh:jda-utilities:3.0.2")
	implementation("com.sedmelluq:lavaplayer:1.3.25")
	implementation("com.sedmelluq:jda-nas:1.1.0")
	implementation("org.languagetool:language-en:4.7")
	implementation("org.scilab.forge:jlatexmath:1.0.7")
	implementation("com.atilika.kuromoji:kuromoji-ipadic:0.9.0")
	implementation("com.andree-surya:moji4j:1.0.0")
	implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar", "*.aar"))))

	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	testImplementation("org.springframework.boot:spring-boot-starter-test") {
		exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
	}
	runtimeOnly("com.h2database:h2")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "1.8"
	}
}
