package com.example.demo_kt.services

import com.example.demo_kt.models.Author
import com.example.demo_kt.models.Book
import com.example.demo_kt.repositories.AuthorRepository
import com.example.demo_kt.repositories.BookRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import java.util.*


@Service
class BookService(
    private var bookRepository: BookRepository,
    private val authorRepository: AuthorRepository
){

    fun getBooks():List<Book>{return bookRepository.findAll() as List<Book> }

    fun getBookById(book_id:Long): Optional<Book> {
        var exist:Boolean = bookRepository.existsById(book_id)
        if (!exist)
            throw IllegalStateException("Book with id: " + book_id + " does not exist!")
        return bookRepository.findById(book_id)
    }

    fun addNewBook(book: Book): Boolean {
        val bookOptional = bookRepository
                .findBookByName(book.name)
        check(!bookOptional!!.isPresent) { "name taken" }
        bookRepository.save(book)
        return true
    }

    fun deleteBook(BookId: Long) {
        val exists = bookRepository.existsById(BookId)
        check(exists) { "student with id" + BookId + "does not exist" }
        bookRepository.deleteById(BookId)
    }

    @Transactional
    fun updateBook(BookId: Long, name: String, annot: String): Boolean {
        val book = bookRepository.findById(BookId)
                .orElseThrow { java.lang.IllegalStateException("student with id" + BookId + "does not exist") }
        if (name != null && name.length > 0 && book.name != name) {
            book.name = name
        }
        book.annot = annot
        return true
    }
//    fun getBookByAuthorId(AuthorId: Long): List<Book?>? {
//        val exists: Boolean = authorRepository.existsById(AuthorId)
//        check(exists) { "Author with id" + AuthorId + "does not exist" }
//        return bookRepository.findBookByAuthor_id(AuthorId)
//    }
//
//
//    fun assignAuthorToBook(BookId: Long?, AuthorId: Long?): Book? {
//        var authors: MutableSet<Author?>? = null
//        val book = bookRepository.findById(BookId!!).get()
//        val author: Author = authorRepository.findById(AuthorId).get()
//        authors = book.authors
//        authors!!.add(author)
//        book.setAuthors(authors)
//        return bookRepository.save(book)
//}
}