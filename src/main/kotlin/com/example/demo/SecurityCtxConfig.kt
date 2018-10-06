package com.example.demo

import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

@Configuration
@EnableWebSecurity
class SecurityCtxConfig : WebSecurityConfigurerAdapter() {
    override fun configure(web: WebSecurity) {
        web.ignoring()
                .antMatchers(HttpMethod.GET,
                        "/internal")
    }
}