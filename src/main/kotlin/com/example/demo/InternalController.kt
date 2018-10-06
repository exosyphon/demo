package com.example.demo

import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@CrossOrigin
class InternalController() {

    @GetMapping("/internal")
    fun internalRoute(): DataResponse {
        return DataResponse("bob dylan")
    }
}

data class DataResponse(
        val data: String
)