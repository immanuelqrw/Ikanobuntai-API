package com.immanuelqrw.ikanobuntai.api

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication



@SpringBootApplication
class Application

@Autowired
private lateinit var config: YAMLConfig

fun main(args: Array<String>) {
    SpringApplication.run(Application::class.java, *args)
}
