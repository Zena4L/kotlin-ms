package org.clement.customservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication

class CustomServiceApplication

fun main(args: Array<String>) {
    runApplication<CustomServiceApplication>(*args)
}
