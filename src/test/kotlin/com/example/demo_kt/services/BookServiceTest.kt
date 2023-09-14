package com.example.demo_kt.services


import com.example.demo_kt.models.Author
import com.example.demo_kt.models.Book
import com.example.demo_kt.repositories.AuthorRepository
import com.example.demo_kt.repositories.BookRepository
import jakarta.transaction.Transactional
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.function.Executable
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

//@RunWith(SpringRunner::class)
@SpringBootTest
internal class BookServiceTest {
    @Mock
    private lateinit var bookRepository: BookRepository

    @Mock
    private val authorRepository: AuthorRepository? = null

    @InjectMocks
    private lateinit var  bookService: BookService

    @InjectMocks
    private val authorService: AuthorService? = null
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
    fun getAllBooks(){
        Mockito.`when`(bookRepository.findAll()).thenReturn(mutableListOf(book))
        Assertions.assertNotNull(bookService.getBooks())
    }
    @Test
    @Transactional
    fun getBookById(){
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
    @Autowired
    fun updateBook() {
        var book_id: Long = book!!.book_id
        Mockito.`when`(bookRepository.findById(book_id)).thenReturn(Optional.ofNullable(book))
        Mockito.`when`(bookRepository.save(book!!)).thenReturn(book)
        Assertions.assertNotNull(bookService.updateBook(book_id, "Arbuz", "Norm"))
    }

    @Test
    fun deleteBook(){
        var book_id: Long = 3L
        var book:Book = Book(book_id,
            null,
        "Kniga",
        "akdakd",
        LocalDate.of(2013, Month.SEPTEMBER, 14),
        "QIJASFAFJLA")
        Mockito.`when`(bookRepository.findById(any())).thenReturn(Optional.ofNullable(book))
        Mockito.`when`(bookRepository.save(book)).thenReturn(book)
        Mockito.doNothing().`when`(bookRepository.deleteById(any()))

        Assertions.assertAll(Executable {
            bookService.deleteBook(
                book_id
            )
        })
    }

//    @Test
//    fun DeleteBook() {
//        val book_id = 3L
//        val book = Book(
//            book_id,
//            null,
//            "Kniga",
//            "akdakd",
//            LocalDate.of(2013, Month.SEPTEMBER, 14),
//            "QIJASFAFJLA"
//        )
//        Mockito.`when`(bookRepository.findById(ArgumentMatchers.any())).thenReturn(Optional.ofNullable(book))
//        Mockito.`when`(bookRepository.save(book)).thenReturn(book)
//        Mockito.`when`(bookRepository.existsById(ArgumentMatchers.any())).thenReturn(true)
//        Mockito.doNothing().`when`<Any?>(bookRepository).delete(book)
//        Assertions.assertAll(Executable {
//            bookService.deleteBook(
//                book_id
//            )
//        })


    }

//private fun <T> OngoingStubbing<T>.thenReturn() {
//
//}
//    @Test
    //    public void assignation() {
    //        Set<Author> authors = null;
    //        when(bookRepository.findById(any())).thenReturn(Optional.ofNullable(book));
    //        //when(bookRepository.save(book)).thenReturn(book);
    //        //when(bookRepository.existsById(any())).thenReturn(true);
    //        when(authorRepository.existsById(any())).thenReturn(Optional.ofNullable(authors));
    //    }
