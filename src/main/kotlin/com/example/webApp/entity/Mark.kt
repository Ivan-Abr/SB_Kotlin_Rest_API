package com.example.webApp.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.persistence.*

@Entity
@Table(name = "mark")
class Mark (
    @Id
    @JsonProperty("mark_id")
    @Column(name = "mark_id")
    var markId: Long,

    @JsonProperty("mark_name")
    @Column(name = "mark_name")
    var markName: String,

    @JsonProperty("mark_value")
    @Column(name = "mark_value")
    var markValue: Int,

    @OneToMany(mappedBy = "question")
    @JsonIgnore
    var answers: Set<Answer>? = HashSet(),

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "question_id")
    var question: Question? = null,
){
}