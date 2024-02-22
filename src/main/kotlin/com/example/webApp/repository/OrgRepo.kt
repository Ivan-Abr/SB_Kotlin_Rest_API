package com.example.webApp.repository

import com.example.webApp.entity.Organization
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface OrgRepo:  JpaRepository<Organization, Long>{
    @Query("SELECT o from Organization o WHERE o.orgName =?1")
    fun findOrganizationByName(name:String?): Optional<Organization?>?
}