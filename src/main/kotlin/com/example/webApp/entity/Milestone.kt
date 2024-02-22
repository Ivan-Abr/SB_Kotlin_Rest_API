package com.example.webApp.entity

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import java.time.LocalDate
import java.time.Year
import java.util.Date


@Entity
@Table(name = "milestone")
data class Milestone (
    @Id
    @JsonProperty("milestone_id")
    @Column(name = "milestone_id")
    var milestoneId: Long = 0L,

    @OneToMany(mappedBy = "milestone")
    var answers: Set<Answer?>? = HashSet(),

    @JsonProperty("date_from")
    @Column(name = "date_from")
    var dateFrom: LocalDate,

    @JsonProperty("date_to")
    @Column(name = "date_to")
    var dateTo: LocalDate,

    @JsonProperty("year")
    @Column(name = "year")
    var year: Year

){
    override fun toString(): String {
        return "Milestone(milestoneId=$milestoneId, dateFrom=$dateFrom, dateTo=$dateTo, year=$year)"
    }
}