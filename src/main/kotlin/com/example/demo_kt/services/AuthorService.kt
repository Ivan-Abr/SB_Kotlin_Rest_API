package com.example.demo_kt.services

import com.example.demo_kt.models.Author
import com.example.demo_kt.repositories.AuthorRepository
import jakarta.transaction.Transactional
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Service
import java.util.*


@Service
class AuthorService(private var authorRepository: AuthorRepository) {

    fun getAuthors(): List<Author> { return authorRepository.findAll() }

    fun getAuthorById(authorId: Long): Optional<Author> {
        val exists = authorRepository.existsById(authorId)
        check(exists) { "Author with id" + authorId + "does not exist" }
        return authorRepository.findById(authorId)
    }

    fun addNewAuthor(author: Author): Boolean {
        val authorOptional: Optional<Author?>? = authorRepository
            .findAuthorByEmail(author.email)
        check(!authorOptional!!.isPresent) { "email.taken" }
        authorRepository.save(author)
        return true
    }

    fun deleteAuthor(AuthorId: Long) {
        val exists = authorRepository.existsById(AuthorId)
        check(exists) { "Student with id" + AuthorId + "does not exist" }
        authorRepository.deleteById(AuthorId)
    }

    @Transactional
    fun updateAuthor(
        AuthorId: Long,
        name: String?,
        email: String?
    ) {
        val author: Author = authorRepository.findById(AuthorId)
            .orElseThrow { IllegalStateException("Student with id" + AuthorId + "does not exist") }
        if (!name.isNullOrEmpty() && author.name != name) { author.name = name }
        if (!email.isNullOrEmpty() && author.email != email) {
            val authorOptional: Optional<Author?>? = authorRepository
                .findAuthorByEmail(email)
            check(!authorOptional!!.isPresent) { "email taken" }
            author.name
        }
    }
}