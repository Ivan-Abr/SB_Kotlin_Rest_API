package com.example.demo_kt.configurations

import com.example.demo_kt.models.Author
import com.example.demo_kt.repositories.AuthorRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.time.LocalDate
import java.time.Month

@Configuration
class AuthorConfig {
    @Bean
    fun commandLineRun(@Autowired repository: AuthorRepository): CommandLineRunner {
        return CommandLineRunner {
            val pushkin = Author(
                1L,
                null,
                "Pushkin",
                "Pushkin@mail.ru",
                LocalDate.of(1799, Month.JUNE, 6),
                34

            )
            val tolstoy = Author(
                2L,
                null,
                "Tolstoy",
                "Tolstoy@mail.ru",
                LocalDate.of(1000, Month.APRIL, 3),
                42

            )
            repository.saveAll(
                listOf(pushkin, tolstoy)
            )
        }
    }
}
