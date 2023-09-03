package com.example.demo_kt.service_tests

import com.example.demo_kt.models.Author
import com.example.demo_kt.models.Book
import com.example.demo_kt.repositories.AuthorRepository
import com.example.demo_kt.repositories.BookRepository
import com.example.demo_kt.services.AuthorService
import com.example.demo_kt.services.BookService
import jakarta.transaction.Transactional
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.springframework.boot.test.context.SpringBootTest
import java.time.LocalDate
import java.time.Month
import java.util.*

@SpringBootTest
class BookServiceTest (

    @Mock
    val bookRepository: BookRepository,

    @Mock
    private val authorRepository: AuthorRepository,


    @InjectMocks
    private val bookService: BookService,

    @InjectMocks
    private val authorService: AuthorService,

    private var book: Book,
    private var author: Author,
)
{
    @BeforeEach
    fun setup() {
        book = Book(
         5L,
            null,
        "Test book",
            "?????",
        LocalDate.of(12, 1, 6),
        "@&^#b24029&#$07242049$#*j39jdsd0")
        author = Author(
            1L,
            null,
            "Autor",
            "Autor@mail.ru",
            LocalDate.of(1000, Month.APRIL, 3),
            45
        )
    }

    @Test
    @Transactional
    fun addBookTest(){
//        val book_id: Long = book.book_id
//        Mockito.`when`(bookRepository.findById(book_id)).thenReturn(Optional.ofNullable(book))
//        Mockito.`when`(bookRepository.save(book)).thenReturn(book)
//        bookService.addNewBook(book)
//        var added_book = bookService.getBookById(book_id)
//        Assertions.assertNotNull(added_book)
        Assertions.assertTrue(bookService.addNewBook(book))
    }

    @Test
    @Transactional
    fun getAllBooksTest(){
        Assertions.assertNotNull(bookService.getBooks())
    }

    @Test
    fun getBookByIdTest(){
        val test_book = Book(
            6L,
            null,
            "How2Flex",
            "Pablo",
            LocalDate.of(2013, Month.FEBRUARY, 1),
            "???????"
        )
        Mockito.`when`(bookRepository.findById(ArgumentMatchers.any())).thenReturn(Optional.ofNullable(test_book))
        Mockito.`when`(bookRepository.existsById(ArgumentMatchers.any())).thenReturn(true)
        val book1 = bookService.getBookById(6L)
        Assertions.assertEquals(book1.get().name, "How2Flex")
        Assertions.assertEquals(book1.get().publisher, "Pablo")
    }




}