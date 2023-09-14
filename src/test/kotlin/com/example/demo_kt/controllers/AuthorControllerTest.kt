package com.example.demo_kt.controllers

import com.example.demo_kt.models.Author
import com.example.demo_kt.models.Book
import com.example.demo_kt.services.AuthorService
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers
import org.mockito.ArgumentMatchers.any
import org.mockito.Mock
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
import java.util.*


@WebMvcTest(controllers = [AuthorController::class])
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension::class)
class AuthorControllerTest(
    @Autowired
    private var mockMvc: MockMvc,
    @Autowired
    private val objectMapper: ObjectMapper
) {

    @MockBean
    private lateinit var authorService: AuthorService

    var author:Author? = null

    @BeforeEach
    fun init(){
        author = Author(
            5L,
            null,
            "",
            "",
            LocalDate.now(),
            0
        )
    }

    @Test
    @Throws(java.lang.Exception::class)
    fun getAuthors() {
        val authors = listOf(author!!)
        Mockito.`when`(authorService.getAuthors()).thenReturn(authors)
        val response = mockMvc.perform(
            MockMvcRequestBuilders.get("/api/v1/author")
                .contentType(MediaType.APPLICATION_JSON)
        )
        response.andExpect(MockMvcResultMatchers.status().isOk)
    }

    @Test
    @Throws(java.lang.Exception::class)
    fun getAuthorById() {
        var authorId: Long = 1L
        Mockito.`when`(authorService.getAuthorById(authorId)).thenReturn(Optional.ofNullable(author))
        val response = mockMvc.perform(
            MockMvcRequestBuilders.get("/api/v1/author/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(author))
        )
        response.andExpect(MockMvcResultMatchers.status().isOk)
    }

    @Test
    @Throws(java.lang.Exception::class)

    fun registerNewAuthor() {
        Mockito.`when`(authorService.addNewAuthor(any(Author::class.java))).thenReturn(author)

        val response = mockMvc.perform(
            MockMvcRequestBuilders.post("/api/v1/author")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(author))
        )
        response.andExpect(MockMvcResultMatchers.status().isOk)
            .andReturn().response.getContentAsString()
    }

    @Test
    @Throws(java.lang.Exception::class)
    fun deleteAuthor() {
        var authorId = 1L
        Mockito.doNothing().`when`(authorService).deleteAuthor(authorId)
        val response = mockMvc.perform(
            MockMvcRequestBuilders.delete("/api/v1/author/1")
                .contentType(MediaType.APPLICATION_JSON)
        )
        response.andExpect(MockMvcResultMatchers.status().isOk)
    }

    @Test
    @Throws(java.lang.Exception::class)
    fun updateAuthor() {
    }
}