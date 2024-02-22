package com.example.webApp.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.persistence.*

@Entity
@Table(name = "organization")
data class Organization(
    @Id
    @JsonProperty("orgId")
    @Column(name = "orgId")
    var orgId: Long = 0L,


    @OneToMany(mappedBy="organization")
    @JsonIgnore
    var answers:Set<Answer?>? = HashSet(),



    @JsonProperty("orgName")
    @Column(name = "orgName", length = 100)
    var orgName: String = "",

    @JsonProperty("orgAnnot")
    @Column(name = "orgAnnot", length = 200)
    var orgAnnot: String = "",

    @JsonProperty("orgContacts")
    @Column(name = "orgContacts", length = 200)
    var orgContacts:String = ""
){
    override fun toString(): String {
        return "Organization(orgId=$orgId, orgName='$orgName', orgAnnot='$orgAnnot', orgContacts='$orgContacts')"
    }
}
