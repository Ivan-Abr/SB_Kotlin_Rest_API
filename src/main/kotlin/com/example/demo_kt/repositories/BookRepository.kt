package com.example.demo_kt.repositories

import com.example.demo_kt.models.Book
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.*
import org.springframework.stereotype.Repository
import java.util.*


@Repository
interface BookRepository: JpaRepository<Book, Long>
{
    @Query("select b from Book b where b.name = ?1")
    fun findBookByName(name: String?): Optional<Book>




//    @Query("""SELECT b FROM Book b
//    INNER JOIN author_book ab ON ab.book_id =  b.book_id
//    INNER JOIN Author a ON a.author_id = ab.author_id
//    WHERE a.author_id = 1
//    """)
//    fun findBookByAuthor_id(AuthorId: Long): List<Book>
}