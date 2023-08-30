package com.example.demo_kt.configurations

import com.example.demo_kt.models.Book
import com.example.demo_kt.repositories.BookRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.time.LocalDate
import java.time.Month


@Configuration
class BookConfig {
    @Bean
    fun commandLineRunner(@Autowired repository: BookRepository): CommandLineRunner {
        return CommandLineRunner {
            val buratino = Book(
                1L,
                "Buratino",
                "PitRes",
                LocalDate.of(1896, Month.APRIL, 13),
                "Priklucheniya derevyanogo"
            )
            val goldenFish = Book(
                    2L,
                    "Golden Fish",
                    "MosPechat",
                    LocalDate.of(1987, Month.FEBRUARY, 13),
                    "QIJASFAFJLA"
            )
            repository.saveAll(
                    listOf(buratino, goldenFish)
            )
        }
    }
}
