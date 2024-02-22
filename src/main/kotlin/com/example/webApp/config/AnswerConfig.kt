package com.example.webApp.config

import com.example.webApp.entity.Answer
import com.example.webApp.repository.AnswerRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class AnswerConfig {
//    @Bean
//    fun commandLineRunner(@Autowired answerRepo: AnswerRepo): CommandLineRunner{
//        return CommandLineRunner {
//            val first_test = Answer(1L,null,null, null)
//            answerRepo.saveAll(listOf(first_test))
//        }
//    }
}