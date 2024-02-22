package com.example.webApp.repository

import com.example.webApp.entity.Milestone
import org.springframework.data.jpa.repository.JpaRepository

interface MilestoneRepo: JpaRepository<Milestone, Long> {

}