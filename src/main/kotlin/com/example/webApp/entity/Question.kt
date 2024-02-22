package com.example.webApp.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.persistence.*

@Entity
@Table(name = "question")
public class Question(
    @Id
    @JsonProperty("question_id")
    @Column(name = "question_id")
    var questionId: Long = 0L,

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "layer_id")
    var layer: Layer? = null,

    @OneToMany
    @JsonIgnore
    var marks: Set<Mark>? = HashSet(),

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "factor_id")
    var factor: Factor,

    @JsonProperty("question_name")
    @Column(name = "question_name", length = 100)
    var questionName: String = "",

    @JsonProperty("question_annot")
    @Column(name = "annot", length = 300)
    var questionAnnot: String = "",

) {


    override fun toString(): String {
        return "Question(questionId=$questionId, layer=$layer, questionName='$questionName', questionAnnot='$questionAnnot')"
    }
}
