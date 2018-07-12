package com.example.demo

import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
interface ThingRepository : CrudRepository<Thing, Long> {
    @Modifying
    @Transactional
    @Query(value = "TRUNCATE thing;", nativeQuery = true)
    fun truncate()
}