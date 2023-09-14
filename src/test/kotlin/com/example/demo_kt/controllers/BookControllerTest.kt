package com.example.demo_kt.controllers

import com.example.demo_kt.models.Book
import com.example.demo_kt.services.BookService
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers.any
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import java.time.LocalDate
import java.time.Month
import java.util.*

@WebMvcTest(controllers = [BookController::class])
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension::class)
class BookControllerTest(
    @Autowired
    private var mockMvc: MockMvc,

    @Autowired
    private val objectMapper: ObjectMapper,
){

    @MockBean
    private lateinit var bookService: BookService

    var book: Book? = null
    @BeforeEach
    fun init() {
        book = Book(
            1L,
            null,
            "Buratino",
            "3 Krota",
            LocalDate.of(1987, Month.FEBRUARY, 13),
            "hoaDHAOHDOAHD"
        )
    }

    @Test
    @Throws(java.lang.Exception::class)
    fun postBook() {
        //Mockito.`when`(bookService.addNewBook(any(Book::class.java))).thenReturn(book)
        Mockito.`when`(bookService.addNewBook(any(Book::class.java))).thenReturn(book)
//        BDDMockito.given(bookService.addNewBook(any())).willAnswer(Answer { invocation: InvocationOnMock ->
//            invocation.getArgument<Any>(
//                0
//            )
//        })
        val response = mockMvc.perform(
            MockMvcRequestBuilders.post("/api/v1/book")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(book))
        )
        response.andExpect(MockMvcResultMatchers.status().isOk())
            .andReturn().response.getContentAsString()
            //.andExpect(content().contentType(MediaType.APPLICATION_JSON))
            //.andExpect(jsonPath("$.bankCode").value("ING"));
            //.andExpect(MockMvcResultMatchers.jsonPath("$.book_name", CoreMatchers.`is`(book!!.name)))
            //.andExpect(MockMvcResultMatchers.jsonPath("$.publisher", CoreMatchers.`is`(book!!.publisher)))
    }

    @Test
    @Throws(java.lang.Exception::class)
    fun getBooks() {
        val books = listOf(book!!)
        Mockito.`when`(bookService.getBooks()).thenReturn(books)
        val response = mockMvc.perform(
            MockMvcRequestBuilders.get("/api/v1/book")
                .contentType(MediaType.APPLICATION_JSON)
        )
        response.andExpect(MockMvcResultMatchers.status().isOk)
    }

    @Test
    @Throws(java.lang.Exception::class)
    fun getBookById(){
        var bookId: Long = 1L
        Mockito.`when`(bookService.getBookById(bookId)).thenReturn(Optional.ofNullable(book))
        val response = mockMvc.perform(
            MockMvcRequestBuilders.get("/api/v1/book/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(book))
        )
        response.andExpect(MockMvcResultMatchers.status().isOk)
    }


    @Test
    @Throws(java.lang.Exception::class)

    fun updateBookTest(){
        var bookId = 1L
        Mockito.`when`(bookService.getBookById(bookId)).thenReturn(Optional.ofNullable(book))
        Mockito.`when`(bookService.updateBook(bookId,"testName","testAnnot")).thenReturn(book)
        val response = mockMvc.perform(
            MockMvcRequestBuilders.put("/api/v1/author/1?book_name=testName&annotation=testAnnot")
                .contentType(MediaType.APPLICATION_JSON)
        )
        response.andExpect(MockMvcResultMatchers.status().isOk)
    }

    @Test
    @Throws(java.lang.Exception::class)
    fun deleteBookTest(){
        var bookId: Long = 1L
        Mockito.doNothing().`when`(bookService).deleteBook(bookId)
        val response = mockMvc.perform(
            MockMvcRequestBuilders.delete("/api/v1/book/1")
                .contentType(MediaType.APPLICATION_JSON)
        )
        response.andExpect(MockMvcResultMatchers.status().isOk)


    }


}