package xyz.treelar.hanamaru

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
class HanamaruApplication

fun main(args: Array<String>) {
	runApplication<HanamaruApplication>(*args)
}
