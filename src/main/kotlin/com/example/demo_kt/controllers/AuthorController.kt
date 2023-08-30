package com.example.demo.authors

import com.example.demo_kt.models.Author
import com.example.demo_kt.services.AuthorService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("api/v1/author")
class AuthorController (private var authorService: AuthorService) {
    @GetMapping
    fun getAuthors(): List<Author>
    {return authorService.getAuthors()}

    @GetMapping("{AuthorId}")
    fun getAuthorById(@PathVariable("AuthorId") AuthorId: Long): Optional<Author> {
        return authorService.getAuthorById(AuthorId)
    }

    @PostMapping
    fun registerNewAuthor(@RequestBody author: Author) {
        authorService.addNewAuthor(author)
    }

    @DeleteMapping("{AuthorId}")
    fun deleteAuthor(@PathVariable("AuthorId") AuthorId: Long) {
        authorService.deleteAuthor(AuthorId)
    }

    @PutMapping("{AuthorId}")
    fun updateAuthor(
        @PathVariable("AuthorId") AuthorId: Long,
        @RequestParam(required = false) name: String?,
        @RequestParam(required = false) email: String?
    ) {
        authorService.updateAuthor(AuthorId, name, email)
    }
}
