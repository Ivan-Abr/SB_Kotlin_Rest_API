package com.example.demo_kt.models

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.persistence.*
import java.time.LocalDate
import kotlin.jvm.Transient


@Entity
@Table(name = "author")
data class Author(
    @Id
    @JsonProperty("author_id")
    @Column(name = "author_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    var author_id: Long = 0L,

    @JsonProperty("author_name")
    @Column(name = "name", length = 100)
    var name: String = "",

    @JsonProperty("email")
    @Column(name = "email", length = 100)
    var email: String = "",

    @JsonProperty("dob")
    @Column(name = "dob", length = 100)
    var dob: LocalDate = LocalDate.now(),

    @JsonProperty("age")
    @Column(name = "age", length = 100)
    @Transient
    var age: Int
) {
    override fun toString(): String {
        return "Author(author_id=$author_id, " +
                "name='$name', " +
                "email='$email', " +
                "dob=$dob, " +
                "age=$age)"
    }




}