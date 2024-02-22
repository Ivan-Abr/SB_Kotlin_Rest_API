package com.example.webApp.config

import com.example.webApp.entity.Layer
import com.example.webApp.repository.LayerRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class LayerConfig {
//    @Bean
//    fun commandLineRunnerLayer(@Autowired layerRepo: LayerRepo):CommandLineRunner{
//        return CommandLineRunner {
//            val orgCulture = Layer(1L,null,"Organizational Culture")
//            val staff = Layer(2L,null,"Staff")
//            layerRepo.saveAll(listOf(orgCulture, staff))
//        }
//    }
}