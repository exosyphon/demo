package com.example.demo

import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@CrossOrigin
class InternalController() {

    @GetMapping("/internal")
    fun internalRoute(): List<BadDataResponse> {
        var list = mutableListOf<BadDataResponse>()
        for (x in 1..10000) {
            list.add(BadDataResponse("hello", "kitty"))
        }

        return list
    }
}

data class BadDataResponse(
        val data: String,
        val tom: String?
)

data class BadDataParsed(
        val data: String,
        val tom: String
)