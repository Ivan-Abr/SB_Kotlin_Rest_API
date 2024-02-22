package com.example.webApp.entity

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import java.time.LocalDate
import java.time.Year
@Entity
class AnsData (
    @Id
    @GeneratedValue
    var ansDataId: Long,

    @JsonProperty("avg_value")
    var avgValue: Double,

    @JsonProperty("layer_name")
    var layerName: String,

    @JsonProperty("factor_short_name")
    var factorShortName: String,

    @JsonProperty("org_name")
    var orgName: String,

    @JsonProperty("year")
    var year: Year
){
}