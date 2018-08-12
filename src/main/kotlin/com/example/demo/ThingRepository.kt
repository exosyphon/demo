package com.example.demo

import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.NoRepositoryBean
import org.springframework.transaction.annotation.Transactional

@NoRepositoryBean
interface BaseThingRepository<T : BaseThing> : CrudRepository<T, Long> {
    fun truncate()
}

interface ThingRepository : BaseThingRepository<Thing> {
    @Modifying
    @Transactional
    @Query(value = "TRUNCATE thing;", nativeQuery = true)
    override fun truncate()
}

interface OtherThingRepository : BaseThingRepository<OtherThing> {
    @Modifying
    @Transactional
    @Query(value = "TRUNCATE other_thing;", nativeQuery = true)
    override fun truncate()
}