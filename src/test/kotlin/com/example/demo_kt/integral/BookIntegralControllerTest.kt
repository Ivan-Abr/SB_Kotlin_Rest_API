package com.example.demo_kt.integral

import com.example.demo_kt.DemoKtApplication
import com.example.demo_kt.models.Author
import com.example.demo_kt.models.Book
import com.example.demo_kt.repositories.AuthorRepository
import com.example.demo_kt.repositories.BookRepository
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.TestPropertySource
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import java.time.LocalDate

//@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = [DemoKtApplication::class])
@AutoConfigureMockMvc
@TestPropertySource(locations = ["classpath:application-test.properties"])
class IntegralTest( @Autowired private val mvc: MockMvc,
                    @Autowired private val bookRepository: BookRepository,
                    @Autowired private val authorRepository: AuthorRepository,
                    @Autowired private val objectMapper: ObjectMapper) {

    lateinit var book: Book
    lateinit var book2: Book
    lateinit var author: Author

    @BeforeEach
    fun initAndSave(){
        book = Book(1L,null, "fst_test","Spring", LocalDate.now(), "normal")
        book2 = Book(2L,null,"sec_test","Spring", LocalDate.now(), "good")
        author = Author(1L, null,"Misha","Mish@mail.ru", LocalDate.now(), 23)
        bookRepository.save(book)
        authorRepository.save(author)
    }
    @Test
    @Throws(java.lang.Exception::class)
    fun getAllBooks(){
        mvc.perform(MockMvcRequestBuilders.get("/api/v1/book")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk)
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
    }

    @Test
    @Throws(java.lang.Exception::class)
    fun getBookById(){
        mvc.perform(MockMvcRequestBuilders.get("/api/v1/book/1")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk)
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
    }

    @Test
    @Throws(java.lang.Exception::class)
    fun postBook(){
        mvc.perform(MockMvcRequestBuilders.post("/api/v1/book")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(book2)))
            .andExpect(status().isOk())
    }

    @Test
    @Throws(java.lang.Exception::class)
    fun updateBook(){
        mvc.perform(MockMvcRequestBuilders
            .put("/api/v1/book/1?name=testName&annotation=testAnnot")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk)
    }

    @Test
    @Throws(java.lang.Exception::class)
    fun assignAuthorToBook(){
        mvc.perform(MockMvcRequestBuilders.put("/api/v1/book/1/author/1")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk)
    }

    @Test
    @Throws(java.lang.Exception::class)
    fun deleteBook(){
        mvc.perform(MockMvcRequestBuilders.delete("/api/v1/book/1")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk)
    }



}
