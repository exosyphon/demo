package com.example.demo

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@CrossOrigin
class ThingController(val thingRepository: ThingRepository) {
    @RequestMapping("/")
    fun index(): ResponseEntity<List<Thing>> {
        thingRepository.save(Thing("name1", "name2"))
        thingRepository.save(Thing("name3", "name2"))
        return ResponseEntity.ok(thingRepository.findAll().toMutableList())
    }
}