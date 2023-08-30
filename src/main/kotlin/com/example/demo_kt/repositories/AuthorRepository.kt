package com.example.demo_kt.repositories

import com.example.demo_kt.models.Author
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.*
import org.springframework.stereotype.Repository
import java.util.*


@Repository
interface AuthorRepository: JpaRepository<Author, Long> {
    @Query("SELECT a from Author a Where a.email = ?1")
    fun findAuthorByEmail(email: String?): Optional<Author?>?

    @Query("SELECT a from Author a WHERE a.author_id =?1")
    fun findAuthorByAuthor_id(author_id: Long?): Optional<Author?>?

    @Query(
        "SELECT CASE WHEN COUNT(a) > 0 THEN " +
                "TRUE ELSE FALSE END " +
                "FROM Author a WHERE a.email = ?1"
    )
    fun selectExistsEmail(email: String?): Boolean?
}