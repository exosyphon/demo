package com.example.demo

import org.springframework.http.*
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.exchange
import org.springframework.web.util.UriComponentsBuilder
import org.springframework.http.client.support.BasicAuthorizationInterceptor

@RestController
@CrossOrigin
class ThingController(val thingRepository: BaseThingRepository<BaseThing>, val restTemplate: RestTemplate) {

    @RequestMapping("/")
    fun index(): ResponseEntity<List<BaseThing>> {
        return ResponseEntity.ok(thingRepository.findAll().toMutableList())
    }

    @GetMapping("/herp/derp")
    fun callAnotherController(): DataResponse {
//        restTemplate.interceptors.add(
//                BasicAuthorizationInterceptor("user", "password"))

        val builder = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/internal")

        val response: ResponseEntity<DataResponse> = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                null,
                DataResponse::class
        )

        return response.body!!
    }
}