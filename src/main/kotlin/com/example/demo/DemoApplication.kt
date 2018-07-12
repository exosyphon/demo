package com.example.demo

import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class DemoApplication {

    @Bean
    fun boot(thingService: ThingService) = CommandLineRunner {
        println("truncating")
        thingService.truncate()
        println("beginning")
        val time = System.currentTimeMillis()
        thingService.doThings()
        val time2 = System.currentTimeMillis()
        println("finished : ${time2 - time} MS")
    }

}

fun main(args: Array<String>) {
    runApplication<DemoApplication>(*args)
}
