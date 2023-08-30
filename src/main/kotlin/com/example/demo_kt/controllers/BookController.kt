package com.example.demo_kt.controllers

import com.example.demo_kt.models.Book
import com.example.demo_kt.services.BookService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.util.*


@RestController
@RequestMapping("api/v1/book")
class BookController(private var bookService: BookService) {

    @GetMapping
    public fun getBooks(): List<Book>{return bookService.getBooks()}

    @GetMapping(path = ["{BookId}"])
    fun getBookById(@PathVariable("BookId") BookId: Long): Optional<Book> {
        return bookService.getBookById(BookId)
    }

    @PostMapping
    fun registerNewBook(@RequestBody book: Book) {
        bookService.addNewBook(book)
    }

    @DeleteMapping(path = ["BookId"])
    fun deleteStudent(@PathVariable("BookId") BookId: Long?) {
        bookService.deleteBook(BookId!!)
    }

    @PutMapping(path = ["{BookId}"])
    fun updateStudent(
            @PathVariable("BookId") BookId: Long?,
            @RequestParam(required = false) name: String?,
            @RequestParam(required = false) annotation: String?) {
        bookService.updateBook(BookId!!, name!!, annotation!!)
    }

//    @PutMapping(path = ["{BookId}/author/{AuthorId}"])
//    fun assignAuthorToBook(
//            @PathVariable BookId: Long?,
//            @PathVariable AuthorId: Long?
//    ): Book? {
//        return bookService.assignAuthorToBook(BookId, AuthorId)
//    }
}