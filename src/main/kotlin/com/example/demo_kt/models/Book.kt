package com.example.demo_kt.models

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name="book")
data class Book(@Id
                @JsonProperty("id")
                @Column(name = "book_id")
                @GeneratedValue(strategy = GenerationType.AUTO)
                var book_id: Long = 0L,

                @JsonProperty("name")
                @Column(name = "name", length = 100)
                var name: String = "",

                @JsonProperty("publisher")
                @Column(name = "publisher", length = 200)
                var publisher: String = "",

                @JsonProperty("dob")
                @Column(name = "dob", length = 100)
                var dob: LocalDate = LocalDate.now(),

                @JsonProperty("annotation")
                @Column(name = "annotation", length = 1000)
                var annot:String = ""

) {
    fun Book() {}
//    constructor(book_id: Long, name: String, publisher: String, dob: LocalDate, annot: String) {
//        this.book_id = book_id
//        this.name = name
//        this.publisher = publisher
//        this.dob = dob
//        this.annot = annot
//    }


    override fun toString(): String {
        return "Book(book_id=$book_id, name='$name', publisher='$publisher', dob=$dob, annot='$annot')"
    }



}