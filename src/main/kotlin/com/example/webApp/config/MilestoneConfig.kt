package com.example.webApp.config

import com.example.webApp.entity.Milestone
import com.example.webApp.repository.MilestoneRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.time.LocalDate
import java.time.Month
import java.time.Year

@Configuration
class MilestoneConfig {
//    @Bean
//    fun commandLineRunnerMilestone(@Autowired milestoneRepo: MilestoneRepo): CommandLineRunner{
//        return CommandLineRunner {
//            val firstKvartal  = Milestone(
//                1L,
//                null,
//                LocalDate.of(2011,Month.DECEMBER, 10),
//                LocalDate.of(2012,Month.APRIL, 1),
//                Year.of(2012 )
//            )
//            milestoneRepo.saveAll(listOf(firstKvartal))
//        }
//    }
}