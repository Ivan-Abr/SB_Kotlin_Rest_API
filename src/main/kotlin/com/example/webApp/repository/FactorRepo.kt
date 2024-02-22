package com.example.webApp.repository

import com.example.webApp.entity.Factor
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.*

interface FactorRepo: JpaRepository<Factor, Long> {
    @Query("SELECT f from Factor f WHERE f.factorName = ?1")
    fun findFactorByName(name:String?): Optional<Factor?>?
}