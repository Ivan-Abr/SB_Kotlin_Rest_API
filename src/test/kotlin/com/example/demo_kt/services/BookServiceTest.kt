package com.example.demo_kt.services


import com.example.demo_kt.models.Author
import com.example.demo_kt.models.Book
import com.example.demo_kt.repositories.AuthorRepository
import com.example.demo_kt.repositories.BookRepository
import jakarta.transaction.Transactional
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.*
import org.mockito.ArgumentMatchers.any
import org.springframework.boot.test.context.SpringBootTest
import java.time.LocalDate
import java.time.Month
import java.util.*

@SpringBootTest
internal class BookServiceTest(
    //@Autowired val bookService: BookService
) {
    @Mock
    private lateinit var bookRepository: BookRepository

    @Mock
    private val authorRepository: AuthorRepository? = null

    @InjectMocks
    private lateinit var bookService: BookService

    //
//    @InjectMocks
//    private val authorService: AuthorService? = null
    private var book: Book? = null
    private var author: Author? = null

    @BeforeEach
    fun setup() {
        book = Book(
            5L,
            null,
            "Test book",
            "?????",
            LocalDate.of(12, 1, 6),
            "@&^#b24029&#$07242049$#*j39jdsd0"
        )
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
    fun addNewBook() {
        Mockito.`when`(bookRepository.save(book!!)).thenReturn(book)
        val book1: Book? = bookService.addNewBook(book)
        Assertions.assertNotNull(book1)
    }


    @Test
    @Transactional
    fun getAllBooks() {
        Mockito.`when`(bookRepository.findAll()).thenReturn(mutableListOf(book))
        Assertions.assertNotNull(bookService.getBooks())
    }

    @Test
    @Transactional
    fun getBookByIdTest() {
        var test_book = Book(
            6L,
            null,
            "How2Flex",
            "Pablo",
            LocalDate.of(2013, Month.FEBRUARY, 1),
            "???????"
        )
        Mockito.`when`(bookRepository.findById(ArgumentMatchers.any())).thenReturn(Optional.ofNullable(test_book))
        Mockito.`when`(bookRepository.existsById(ArgumentMatchers.any())).thenReturn(true)
        val book1: Optional<Book> = bookService.getBookById(6L)
        Assertions.assertEquals(book1.get().name, "How2Flex")
        Assertions.assertEquals(book1.get().publisher, "Pablo")
    }

    @Test
    @Transactional
    fun updateBook() {
        var book_id: Long = book!!.book_id
        Mockito.`when`(bookRepository.findById(book_id)).thenReturn(Optional.ofNullable(book))
        Mockito.`when`(bookRepository.save(book!!)).thenReturn(book)
        Assertions.assertNotNull(bookService.updateBook(book_id, "Arbuz", "Norm"))
    }

    @Test
    @Transactional
    fun deleteBook() {
        val bookId = 3L
        val noneBookId = 3L
        Mockito.doNothing().`when`(bookRepository).deleteById(any(Long::class.java))

        Mockito.`when`(bookRepository.existsById(bookId)).thenReturn(true)
        Assertions.assertDoesNotThrow { bookService.deleteBook(bookId) }

        Mockito.`when`(bookRepository.existsById(noneBookId)).thenReturn(false)
        Assertions.assertThrows(IllegalArgumentException::class.java) {
            bookService.deleteBook(noneBookId)
        }
    }

//    @Test
//    fun deleteBook(){
//        // Arrange
//        val bookId: Long = 1
//
//        Mockito.`when`(bookRepository.existsById(bookId)).thenReturn(true)
//
//        // Act
//        bookService.deleteBook(bookId)
//
//        // Assert
//        verify(bookRepository).existsById(bookId)
//        verify(bookRepository).deleteById(bookId)
//    }

//    @Test
//    fun deleteBook() {
//        // Arrange
//        val bookId: Long = 2
//        val exists = false
//
//        Mockito.`when`(bookRepository.existsById(bookId)).thenReturn(true)
//
//        // Act and Assert
//        // Act and Assert
//        assertThrows<IllegalArgumentException> { bookService.deleteBook(bookId) }

    //@Test
//fun DeleteBook() {
//    val book_id = 3L
//    val book = Book(
//        book_id,
//        null,
//        "Kniga",
//        "akdakd",
//        LocalDate.of(2013, Month.SEPTEMBER, 14),
//        "QIJASFAFJLA"
//    )
//    Mockito.`when`(bookRepository.findById(any())).thenReturn(Optional.ofNullable(book))
//    Mockito.`when`(bookRepository.save(book)).thenReturn(book)
//    Mockito.`when`(bookRepository.existsById(any())).thenReturn(true)
//    Mockito.doNothing().`when`(bookRepository.delete(book))
//    Assertions.assertAll(Executable {
//        bookService.deleteBook(
//            book_id
//        )
//    })
//}
    @Test
    @Transactional
    fun asingnAuthorToBook() {


        Mockito.`when`(bookRepository.findById(any())).thenReturn(Optional.ofNullable(book))
        Mockito.`when`(authorRepository?.findById(any())).thenReturn(Optional.ofNullable(author))
        Mockito.`when`(bookRepository.save(any())).thenReturn(book)

        val savedCaptor = ArgumentCaptor.forClass(Book::class.java)

        Assertions.assertDoesNotThrow {
            bookService.assignAuthorToBook(book!!.book_id, author!!.author_id)
        }

        Mockito.verify(bookRepository).save(savedCaptor.capture());
        Assertions.assertTrue(savedCaptor.value.authors!!.contains(author))
    }


}

