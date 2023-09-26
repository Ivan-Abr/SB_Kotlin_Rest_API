package com.example.demo_kt.repositories

import com.example.demo_kt.models.Book
import jakarta.transaction.Transactional
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.time.LocalDate
import java.time.Month

@SpringBootTest
class BookRepoTest (@Autowired var bookRepository: BookRepository) {

    @Test
    @Transactional
    public fun bookRepoSaveTest(){
        val book = Book(
            3L,
            null,
            "Arbuz",
            "West",
            LocalDate.of(1987, Month.FEBRUARY, 13),
            "Neploho"
        )
        var savedBook: Book = bookRepository.save(book)
        Assertions.assertNotNull(savedBook)

    }

}