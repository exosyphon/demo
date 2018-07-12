package com.example.demo

import org.springframework.data.jpa.repository.Modifying
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager

@Service
class ThingService(val entityManager: EntityManager, val thingRepository: ThingRepository) {

    @Transactional
    @Modifying
    fun doThings() {
        entityManager.flush()
        for (i in 0..500_000) {
            val thing = Thing("$i", "name $i")
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
        for (i in 0..500_000) {
            val thing = Thing("$i", "name $i")
            thingRepository.save(thing)
        }
    }

    fun truncate() {
        thingRepository.truncate()
    }
}