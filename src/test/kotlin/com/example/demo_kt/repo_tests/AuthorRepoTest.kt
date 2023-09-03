package com.example.demo_kt.repo_tests

import com.example.demo_kt.models.Author
import com.example.demo_kt.repositories.AuthorRepository
import jakarta.transaction.Transactional
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.time.LocalDate
import java.time.Month


@SpringBootTest
class AuthorRepoTest(@Autowired var authorRepository: AuthorRepository) {

    @Test
    @Transactional
    public fun authorRepoSaveTest(){
        val author = Author(
            4L,
            null,
            "Duhoslav",
            "DHSLV@mail.ru",
            LocalDate.of(2003, Month.MAY, 16),
            21
        )
        var savedAuthor: Author = authorRepository.save(author)
        Assertions.assertNotNull(savedAuthor)
    }
}