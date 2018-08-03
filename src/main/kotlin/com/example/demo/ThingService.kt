package com.example.demo

import org.springframework.batch.item.ItemWriter
import org.springframework.data.jpa.repository.Modifying
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.sql.Time
import java.sql.Timestamp
import java.time.LocalTime
import javax.persistence.EntityManager

@Service
class ThingService(val entityManager: EntityManager, val thingRepository: ThingRepository, val itemWriter: ItemWriter<Thing>, val config: DataIngestConfig) {

    fun doThingsFaster() {
        println(config.get("bob"))
        println(config.get("tom"))
        var list = mutableListOf<Thing>()
        for (i in 0..500_000) {
            list.add(Thing("$i", LocalTime.parse("00:33:44.123456789")))
        }
        itemWriter.write(list)
    }

    @Transactional
    @Modifying
    fun doThings() {
        entityManager.flush()
        for (i in 0..500_000) {
            val thing = Thing("$i", LocalTime.parse("00:33:44.123456789"))
            entityManager.persist(thing)
            if (i % 20 == 0) {
                entityManager.flush()
                entityManager.clear()
            }
        }
        entityManager.flush()
        entityManager.clear()
    }

    fun doThingsSlow() {
        var thing = Thing("1", LocalTime.parse("00:33:44.123456789"))
        thingRepository.save(thing)
        thing.secondName = LocalTime.parse("11:11:11.1111111")
        thingRepository.save(thing)

        println(thing)
        println(thing.secondName)
    }

    fun truncate() {
        thingRepository.truncate()
    }
}