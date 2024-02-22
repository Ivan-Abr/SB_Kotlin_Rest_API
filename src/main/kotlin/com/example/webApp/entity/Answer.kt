package com.example.webApp.entity

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.persistence.*


@Entity
@Table(name = "answer")
public class Answer(
    @Id
        @JsonProperty("answer_id")
        @Column(name = "answer_id")
        var answerId: Long = 0L,

    @ManyToOne
        @JoinColumn(name="mark_id")
        var question: Mark? = null,

    @ManyToOne
        @JoinColumn(name="org_id")
        var organization: Organization? = null,

    @ManyToOne
        @JoinColumn(name="milestone_id")
        var milestone: Milestone?,

        //org_id

        //mark_id
){

}
