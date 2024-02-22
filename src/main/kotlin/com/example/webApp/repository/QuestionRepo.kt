package com.example.webApp.repository

import com.example.webApp.entity.Question
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.Optional

interface QuestionRepo: JpaRepository<Question, Long> {
    @Query("SELECT q from Question q WHERE q.questionName =?1")
    fun findQuestionByName(name:String?): Optional<Question?>?
}