package com.example.demo

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.support.BeanDefinitionRegistry
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.context.annotation.Profile
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@Profile("local")
class ReplaceRepoController(val testingService: TestingService) {
    @Autowired
    private lateinit var context: ConfigurableApplicationContext

    @RequestMapping("/change")
    fun change(): ResponseEntity<String> {
        val beanFactory = context.beanFactory as BeanDefinitionRegistry
        beanFactory.removeBeanDefinition("BaseThingRepository")

        val newBean = testingService.getBaseThingRepository(false)
        context.beanFactory.registerSingleton("BaseThingRepository", newBean)

        return ResponseEntity.ok("Success")
    }
}
