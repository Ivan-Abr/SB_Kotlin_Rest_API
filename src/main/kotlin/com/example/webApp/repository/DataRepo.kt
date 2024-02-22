package com.example.webApp.repository

import com.example.webApp.entity.Answer
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface DataRepo:JpaRepository<Answer, Long> {

}