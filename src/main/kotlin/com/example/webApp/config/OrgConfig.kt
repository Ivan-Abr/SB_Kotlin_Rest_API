package com.example.webApp.config

import com.example.webApp.entity.Organization
import com.example.webApp.repository.OrgRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class OrgConfig {
//    @Bean
//    fun commandLineRunnerOrg(@Autowired orgRepo: OrgRepo): CommandLineRunner{
//        return CommandLineRunner{
//            val tander = Organization(1L,
//                    null,
//                    "OAO Tander",
//                    "Magnit",
//                    "gallitsky@yandex.ru")
//
//            orgRepo.saveAll(listOf(tander))
//        }
//    }
}