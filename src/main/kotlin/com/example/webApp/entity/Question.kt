package com.example.webApp.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.persistence.*

@Entity
@Table(name = "question")
public class Question(
    @Id
    @JsonProperty("questionId")
    @Column(name = "questionId")
    var questionId: Long = 0L,

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "layerId")
    var layer: Layer? = null,

    @OneToMany
    @JsonIgnore
    var marks: Set<Mark>? = HashSet(),

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "factorId")
    var factor: Factor,

    @JsonProperty("questionName")
    @Column(name = "questionName", length = 100)
    var questionName: String = "",

    @JsonProperty("questionAnnot")
    @Column(name = "questionAnnot", length = 300)
    var questionAnnot: String = "",

) {

    @get:JsonProperty("layerId")
    val layerId: Long
            get(){
                return this.layer!!.layerId
            }



    @get:JsonProperty("marksIds")
    val marksIds: List<Long>?
    get(){
        return this.marks!!.map { mark -> mark.markId }
    }

    @get:JsonProperty()
    val facctorId: Long
    get(){
        return this.factor.factorId
    }




    override fun toString(): String {
        return "Question(questionId=$questionId, layer=$layer, questionName='$questionName', questionAnnot='$questionAnnot')"
    }
}
