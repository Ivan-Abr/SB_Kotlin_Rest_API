package com.example.demo_kt.controllers

import com.example.demo_kt.models.Book
import com.example.demo_kt.services.BookService

import org.springframework.web.bind.annotation.*
import java.util.*


@RestController
@RequestMapping("api/v1/book")
//@Api(description = "Book controller")
class BookController(private var bookService: BookService) {

    @GetMapping
    //@ApiOperation("get all books")
    fun getBooks(): List<Book> {
        return bookService.getBooks()
    }

    @GetMapping(path = ["{BookId}"])
    //@ApiOperation("get one book by id")
    fun getBookById(@PathVariable("BookId") BookId: Long): Optional<Book> {
        return bookService.getBookById(BookId)
    }

    @PostMapping
    //@ApiOperation("adding new book")
    fun registerNewBook(@RequestBody book: Book) {
        bookService.addNewBook(book)
    }

    @DeleteMapping(path = ["{BookId}"])
    //@ApiOperation("deleting book by id")
    fun deleteStudent(@PathVariable("BookId") BookId: Long?) {
        bookService.deleteBook(BookId!!)
    }

    @PutMapping(path = ["{BookId}"])
    //@ApiOperation("changing book's name and annotation")
    fun updateBook(
        @PathVariable("BookId") BookId: Long,
        @RequestParam(required = false) name: String,
        @RequestParam(required = false) annotation: String,
    ) {
        bookService.updateBook(BookId, name, annotation)
    }

    @PutMapping(path = ["{BookId}/author/{AuthorId}"])
    //@ApiOperation("assigning author to book(M:N)")
    fun assignAuthorToBook(
        @PathVariable BookId: Long,
        @PathVariable AuthorId: Long,
    ): Book? {
        return bookService.assignAuthorToBook(BookId, AuthorId)
    }
}