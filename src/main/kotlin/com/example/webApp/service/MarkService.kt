package com.example.webApp.service

import com.example.webApp.entity.Answer
import com.example.webApp.entity.Mark
import com.example.webApp.repository.AnswerRepo
import com.example.webApp.repository.MarkRepo
import org.springframework.stereotype.Service
import java.util.*

@Service
class MarkService(private var markRepo: MarkRepo,
    private  var answerRepo: AnswerRepo) {


    fun getAllMarks():List<Mark>{
        return markRepo.findAll()
    }

    fun getMarkById(markId: Long): Optional<Mark> {
        if (!markRepo.existsById(markId))
            throw IllegalStateException("Mark with id $markId does not exist")
        return markRepo.findById(markId)
    }


    fun postMark(mark: Mark): Mark{
        if (mark == null) throw IllegalStateException("mark is null")
        val orgOptional = markRepo
            .getMarkByMarkName(mark.markName)
        check(!orgOptional!!.isPresent) { "name taken" }
        return markRepo.save(mark)
    }

    fun deleteMark(markId: Long){

        if (!markRepo.existsById(markId))
            throw IllegalArgumentException("mark with id" + markId + "does not exist")

        markRepo.deleteById(markId)
    }


    fun updateMark(markId: Long, markName: String, markValue: Int): Mark?{
        val mark = markRepo.findById(markId)
            .orElseThrow { java.lang.IllegalStateException("student with id" + markId + "does not exist") }
        if (markName != null && markName.isNotEmpty() && mark.markName != markName) {
            mark.markName = markName
        }
        mark.markValue = markValue
       return markRepo.save(mark)
    }

    fun addAnswersToMark(markId: Long,  answerId: Long): Mark?{
        val mark = markRepo.findById(markId).get()
        val answer = answerRepo.findById(answerId).get()
        mark.answers = (mark.answers as MutableSet<Answer>).apply { add(answer) }
        return markRepo.save(mark)
    }

}