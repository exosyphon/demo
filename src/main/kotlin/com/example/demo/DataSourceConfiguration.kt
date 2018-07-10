package com.example.demo

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.cloud.Cloud
import org.springframework.cloud.CloudFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import javax.sql.DataSource

@Configuration
@Profile("cloud")
class DataSourceConfiguration {

    @Bean
    fun cloud(): Cloud {
        return CloudFactory().cloud
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    fun dataSource(): DataSource {
        return cloud().getSingletonServiceConnector(DataSource::class.java, null)
    }
}