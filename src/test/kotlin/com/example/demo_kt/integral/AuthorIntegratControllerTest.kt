package com.example.demo_kt.integral

import com.example.demo_kt.DemoKtApplication
import com.example.demo_kt.models.Author
import com.example.demo_kt.models.Book
import com.example.demo_kt.repositories.AuthorRepository
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
import java.time.LocalDate

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = [DemoKtApplication::class])
@AutoConfigureMockMvc
@TestPropertySource(locations = ["classpath:application-test.properties"])
class AuthorIntegratControllerTest(@Autowired private val mvc: MockMvc,
                                   @Autowired private val authorRepository: AuthorRepository,
                                   @Autowired private val objectMapper: ObjectMapper
) {
    lateinit var author: Author
    lateinit var author2: Author

    @BeforeEach
    fun initAndSave() {
        author = Author(
            1L,
            null,
            "Misha",
            "Mish@mail.ru",
            LocalDate.now(),
            23
        )
        author2 = Author(
            2L,
            null,
            "Semen",
            "Sem@mail.ru",
            LocalDate.now(),
            34
        )
        authorRepository.save(author)
    }

    @Test
    @Throws(java.lang.Exception::class)
    fun getAllAuthors() {
        mvc.perform(
            MockMvcRequestBuilders.get("/api/v1/author")
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
    }

    @Test
    @Throws(java.lang.Exception::class)
    fun getAuthorById(){
        mvc.perform(MockMvcRequestBuilders.get("/api/v1/author/1")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
    }

    @Test
    @Throws(java.lang.Exception::class)
    fun postAuthor(){
        mvc.perform(MockMvcRequestBuilders.post("/api/v1/author")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(author2)))
            .andExpect(MockMvcResultMatchers.status().isOk())
    }


    @Test
    @Throws(java.lang.Exception::class)
    fun updateAuthor(){
        mvc.perform(MockMvcRequestBuilders
            .put("/api/v1/author/1?author_name=testName&email=testMail")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk)
    }


    @Test
    @Throws(java.lang.Exception::class)
    fun deleteAuthor(){
        mvc.perform(MockMvcRequestBuilders
            .delete("/api/v1/author/1")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk)
    }
}