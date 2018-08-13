package com.example.demo

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.springframework.batch.item.ItemWriter
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider
import org.springframework.batch.item.database.JdbcBatchItemWriter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.core.io.ResourceLoader
import javax.sql.DataSource

@SpringBootApplication
class DemoApplication {

    @Autowired
    private lateinit var resourceLoader: ResourceLoader
    @Autowired
    private lateinit var thingRepository: ThingRepository

    @Bean
    fun BaseThingRepository(): BaseThingRepository<*> {
        return thingRepository
    }

    @Bean
    fun boot(thingService: ThingService) = CommandLineRunner {
        println("truncating")
        thingService.truncate()
        println("beginning")
        val time = System.currentTimeMillis()
        thingService.doThingsSlow()
        val time2 = System.currentTimeMillis()
        println("finished : ${time2 - time} MS")
    }

    @Bean
    fun loadConfig(): DataIngestConfig {
        val objectMapper = jacksonObjectMapper()
        objectMapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
        return objectMapper.readValue(resourceLoader.getResource("classpath:data.json").file)
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
