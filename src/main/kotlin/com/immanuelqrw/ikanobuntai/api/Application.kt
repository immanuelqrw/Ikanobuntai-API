package com.immanuelqrw.ikanobuntai.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication


@SpringBootApplication
class Application

fun main() {
    try {
        runApplication<Application>()
    } catch (e: Exception) {
        e.printStackTrace()
    }
}
