package com.example.webApp.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.persistence.*

@Entity
@Table(name = "mark")
class Mark (
    @Id
    @JsonProperty("markId")
    @Column(name = "markId")
    var markId: Long,

    @JsonProperty("markName")
    @Column(name = "markName")
    var markName: String,

    @JsonProperty("markValue")
    @Column(name = "markValue")
    var markValue: Int,

    @OneToMany(mappedBy = "mark")
    @JsonIgnore
    var answers: Set<Answer>? = HashSet(),

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "question_id")
    var question: Question? = null,
){

//    @get:JsonProperty("answerIds")
//    val answerIds: List<Long>?
//        get() {
//            return this.answers?.map { answer -> answer.answerId }
//        }


    @get:JsonProperty("questionId")
    val questionId: Long
        get() {
            return this.question!!.questionId;
        }


    override fun toString(): String {
        return "Mark(markId=$markId, markName='$markName', markValue=$markValue, answers=$answers, question=$question)"
    }
}