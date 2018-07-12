package com.example.demo

import org.springframework.batch.item.ItemWriter
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider
import org.springframework.batch.item.database.JdbcBatchItemWriter
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import javax.sql.DataSource

@SpringBootApplication
class DemoApplication {

    @Bean
    fun boot(thingService: ThingService) = CommandLineRunner {
        println("truncating")
        thingService.truncate()
        println("beginning")
        val time = System.currentTimeMillis()
        thingService.doThingsFaster()
        val time2 = System.currentTimeMillis()
        println("finished : ${time2 - time} MS")
    }

    @Bean
    fun dbWriter(dataSource: DataSource): ItemWriter<Thing> {
        val writer = JdbcBatchItemWriter<Thing>()
        writer.setItemSqlParameterSourceProvider(BeanPropertyItemSqlParameterSourceProvider<Thing>())
        writer.setSql("INSERT INTO thing (name, second_name) VALUES (:name,:secondName)")
        writer.setDataSource(dataSource)
        return writer
    }
}

fun main(args: Array<String>) {
    runApplication<DemoApplication>(*args)
}
