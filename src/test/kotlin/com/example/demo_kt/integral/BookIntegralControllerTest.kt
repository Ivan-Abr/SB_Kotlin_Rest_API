package com.example.demo_kt.integral

import com.example.demo_kt.DemoKtApplication
import com.example.demo_kt.models.Book
import com.example.demo_kt.repositories.BookRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
//import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.TestPropertySource
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import java.time.LocalDate

//@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = [DemoKtApplication::class])
@AutoConfigureMockMvc
@TestPropertySource(locations = ["classpath:application-test.properties"])
class IntegralTest( @Autowired private val mvc: MockMvc,
                    @Autowired private val bookRepository: BookRepository) {

    lateinit var book: Book
    @BeforeEach
    fun initAndSave(){
        book = Book(1L,null, "","", LocalDate.now(), "")
        bookRepository.save(book)
    }
    @Test
    @Throws(java.lang.Exception::class)
    fun getAllBooks(){


        mvc.perform(MockMvcRequestBuilders.get("/api/v1/book")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk)
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
    }

}
