package com.example.demo_kt.services

import com.example.demo_kt.services.AuthorService
import org.junit.jupiter.api.Test

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class AuthorServiceTest(
    @Autowired val authorService: AuthorService
) {

    @Test
    fun getAuthors() {
        println(authorService.getAuthors())
    }
}