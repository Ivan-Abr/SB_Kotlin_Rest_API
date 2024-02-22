package com.example.webApp.service

import com.example.webApp.entity.Factor
import com.example.webApp.entity.Question
import com.example.webApp.repository.FactorRepo
import com.example.webApp.repository.QuestionRepo
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import java.util.Optional

@Service
class FactorService(
    private var factorRepo: FactorRepo,
    private var questionRepo: QuestionRepo
) {

    fun getFactors(): List<Factor>{
        return factorRepo.findAll()
    }

    fun getFactorById(factorId: Long): Optional<Factor>{
        var exist: Boolean = factorRepo.existsById(factorId)
        if(!exist)
            throw IllegalStateException("Factor with this ID does not exist!")
        return factorRepo.findById(factorId)
    }
    @Transactional
    fun addFactor(factor:Factor?): Factor?{
        if (factor == null) return null
        val factorOpt = factorRepo.findFactorByName(factor.factorName)
        check(!factorOpt!!.isPresent){"name taken"}
        return factorRepo.save(factor)
    }
    @Transactional
    fun deleteFactor(factorId: Long) {
        val exists = factorRepo.existsById(factorId)
        if (!exists) {
            throw IllegalArgumentException("factor with id" + factorId + "does not exist")
        }
        factorRepo.deleteById(factorId)
    }


    @Transactional
    fun updateFactor(factorId: Long, factorName: String): Factor? {
        val factor = factorRepo.findById(factorId)
            .orElseThrow { java.lang.IllegalStateException("factor with id" + factorId + "does not exist") }
        if (factorName != null && factorName.isNotEmpty() && factor.factorName != factorName) {
            factor.factorName = factorName
        }

        return factor
    }
    fun assignMarksToFactor(factorId: Long, questionId: Long): Factor?{
        val factor = factorRepo.findById(factorId).get()
        val question = questionRepo.findById(questionId).get()

        factor.questions = (factor.questions as MutableSet<Question?>).apply {
            add(question)
        }
        return  factorRepo.save(factor)

    }




}