package com.example.webApp.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.persistence.*

@Entity
@Table(name = "factor")
class Factor(
    @Id
    @JsonProperty("factorId")
    @Column(name = "factorId")
    var factorId: Long,

    @OneToMany(mappedBy="factor")
    @JsonIgnore
    var questions:Set<Question?>? = HashSet(),

    @JsonProperty("factorName")
    @Column(name = "factorName")
    var factorName:String,

    @JsonProperty("factorShortName")
    @Column(name = "factorShortName")
    var factorShortName: String

) {

    @get:JsonProperty("questionsIds")
    val questionsIds: List<Long>?
        get() {
            return this.questions!!.map { question -> question!!.questionId  }
        }
}