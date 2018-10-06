package com.example.demo

import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.http.client.support.BasicAuthorizationInterceptor
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate

@RestController
@CrossOrigin
class ThingController(val thingRepository: BaseThingRepository<BaseThing>, val restTemplate: RestTemplate) {

    @RequestMapping("/")
    fun index(): ResponseEntity<List<BaseThing>> {
        return ResponseEntity.ok(thingRepository.findAll().toMutableList())
    }

    @GetMapping("/herp/derp")
    fun callAnotherController(): List<BadDataParsed> {
        restTemplate.interceptors.add(
                BasicAuthorizationInterceptor("user", "password"))

        var response: ResponseEntity<List<BadDataParsed>>
        try {
            response = restTemplate.exchange(
                    "http://localhost:8080/internal",
                    HttpMethod.GET,
                    null,
                    object : ParameterizedTypeReference<List<BadDataParsed>>() {}
            )
        } catch (e: Exception) {
            e.printStackTrace()
            return emptyList()
        }

        return response?.body!!
    }
}