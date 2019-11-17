package xyz.treelar.hanamaru

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class HanamaruApplication

fun main(args: Array<String>) {
	runApplication<HanamaruApplication>(*args)
}
