package com.example.webApp.repository

import com.example.webApp.entity.Mark
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.Optional

interface MarkRepo: JpaRepository<Mark, Long> {
    @Query("SELECT m from Mark m WHERE m.markName = ?1")
    fun getMarkByMarkName(name: String?): Optional<Mark>
}