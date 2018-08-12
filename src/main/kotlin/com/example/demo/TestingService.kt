package com.example.demo

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Service

@Service
@Profile("local")
class TestingService {
    @Autowired
    private lateinit var otherThingRepository: OtherThingRepository
    @Autowired
    private lateinit var thingRepository: ThingRepository

    fun getBaseThingRepository(useDefault: Boolean): BaseThingRepository<*> {
        if (useDefault) {
            return thingRepository
        } else {
            return otherThingRepository
        }
    }
}