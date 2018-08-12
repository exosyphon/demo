package com.example.demo

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@CrossOrigin
class ThingController(val thingRepository: BaseThingRepository<BaseThing>) {
    @RequestMapping("/")
    fun index(): ResponseEntity<List<BaseThing>> {
        return ResponseEntity.ok(thingRepository.findAll().toMutableList())
    }
}