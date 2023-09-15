package com.example.demo_kt.services

import com.example.demo_kt.models.Author
import com.example.demo_kt.repositories.AuthorRepository
import com.example.demo_kt.services.AuthorService
import jakarta.transaction.Transactional
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers
import org.mockito.ArgumentMatchers.any
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.time.LocalDate
import java.time.Month
import java.util.*

@SpringBootTest
class AuthorServiceTest(
    @Autowired val authorService: AuthorService
) {

    @Mock
    private lateinit var authorRepository: AuthorRepository

    @InjectMocks


    private var author:Author? = null

    @BeforeEach
    fun setup(){
        author = Author(
            1L,
            null,
            "Autor",
            "Autor@mail.ru",
            LocalDate.of(1000, Month.APRIL, 3),
            24
        )
    }

    @Test
    @Transactional
    fun addNewAthor(){
        Mockito.`when`(authorRepository.save(author!!)).thenReturn(author)
        val author1: Author? = authorService.addNewAuthor(author!!)
    }

    @Test
    @Transactional
    fun getAllAuthors() {
        Mockito.`when`(authorRepository.findAll()).thenReturn(mutableListOf(author))
        Assertions.assertNotNull(authorService.getAuthors())
    }

    @Test
    @Transactional
    fun getAuthorById(){
        var test_author = Author(
            5L,
            null,
            "Dushnila",
            "Dushnil@mail.ru",
            LocalDate.of(1000, Month.APRIL, 3),
            24
        )
        Mockito.`when`(authorRepository.findById(any())).thenReturn(Optional.ofNullable(test_author))
        Mockito.`when`(authorRepository.existsById(any())).thenReturn(true)
        val author1: Optional<Author> = authorService.getAuthorById(5L)
        Assertions.assertEquals(author1.get().name,"Dushnila")
        Assertions.assertEquals(author1.get().email, "Dushnil@mail.ru")
    }

    @Test
    @Transactional
    fun updateAuthor(){
        var author_id: Long = author!!.author_id
        Mockito.`when`(authorRepository.findById(author_id)).thenReturn(Optional.ofNullable(author))
        Mockito.`when`(authorRepository.save(author!!)).thenReturn(author)
        Assertions.assertNotNull(authorService.updateAuthor(author_id, "Shinshilla", "Shinshill@mail.com"))
    }

}