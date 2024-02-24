package com.example.webApp.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.persistence.*


@Entity
@Table(name = "answer")
public class Answer(
    @Id
        @JsonProperty("answerId")
        @Column(name = "answerId")
        var answerId: Long,

    @ManyToOne
    @JsonIgnore
        @JoinColumn(name="markId")
        var mark: Mark?,

    @ManyToOne
    @JsonIgnore
        @JoinColumn(name="orgId")
        var organization: Organization?,

    @ManyToOne
    @JsonIgnore
        @JoinColumn(name="milestoneId")
        var milestone: Milestone?,

        //org_id

        //mark_id
){


    @get:JsonProperty("markId")
    val markId: Long
        get() {
            return this.mark!!.markId;
        }

    @get:JsonProperty("orgId")
    val orgId: Long
        get() {
            return this.organization!!.orgId
        }

    @get: JsonProperty("milestoneId")
    val milestoneId: Long
        get() {
            return this.milestone!!.milestoneId
        }
}
