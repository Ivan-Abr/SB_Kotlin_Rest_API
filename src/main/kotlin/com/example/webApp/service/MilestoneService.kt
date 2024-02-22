package com.example.webApp.service

import com.example.webApp.entity.Answer
import com.example.webApp.entity.Milestone
import com.example.webApp.repository.AnswerRepo
import com.example.webApp.repository.MilestoneRepo
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.util.Optional

@Service
class MilestoneService (
    private var milestoneRepo: MilestoneRepo,
    private var answerRepo: AnswerRepo
){
    fun getMileSt(): List<Milestone> {
        return milestoneRepo.findAll()
    }


    fun getMileStById(milesId: Long): Optional<Milestone>{
        if (!milestoneRepo.existsById(milesId))
            throw IllegalStateException("Milestone with id: $milesId does not exist")
        return milestoneRepo.findById(milesId)
    }

    fun addNewMileSt(milestone: Milestone): Milestone?{
        if (milestone == null) return null
        return milestoneRepo.save(milestone)
    }

    @Transactional
    fun deleteMileSt(milesId: Long){
        if (!milestoneRepo.existsById(milesId))
            throw IllegalStateException("Milestone with id: $milesId does not exist")
        milestoneRepo.deleteById(milesId)
    }


    @Transactional
    fun updateMileSt(milesId: Long, dateFrom: LocalDate, dateTo: LocalDate): Milestone? {
        val milestone = milestoneRepo.findById(milesId)
            .orElseThrow{java.lang.IllegalStateException("Milestone with id: $milesId does not exist")}
        if (dateFrom!=null && milestone.dateFrom!= dateFrom){milestone.dateFrom = dateFrom}
        if (dateTo!=null && milestone.dateTo!= dateTo){milestone.dateTo = dateTo}
        return milestone
    }

    @Transactional
    fun addAnswerToMilestone(milesId: Long, answerId: Long): Milestone?{
        val milestone = milestoneRepo.findById(milesId).get()
        val answer = answerRepo.findById(answerId).get()
        milestone.answers = (milestone.answers as MutableSet<Answer?>)
            .apply { add(answer) }
        return milestoneRepo.save(milestone)
    }


}