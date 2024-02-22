package com.example.webApp.service

import com.example.webApp.entity.Answer
import com.example.webApp.repository.AnswerRepo
import com.example.webApp.repository.OrgRepo
import org.springframework.stereotype.Service
import java.lang.reflect.Field
import java.util.*
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType

@Service
class AnswerService(private var answerRepo: AnswerRepo,
//                    private var dataRepo: AnsDataRepo,
                    private var orgRepo: OrgRepo) {
    fun getBooks(): List<Answer> {
        return answerRepo.findAll() as List<Answer>
    }

    fun getAnswerById(answerId: Long): Optional<Answer> {
        var exist: Boolean = answerRepo.existsById(answerId)
        if (!exist)
            throw IllegalStateException("Answer with id: $answerId does not exist!")
        return answerRepo.findById(answerId)
    }

    fun getAllDataByLayerOrg(layerId: Long, orgId: Long): Object{
        return answerRepo.getAllByLayerOrg(layerId, orgId)
    }

    fun getAllDataByFactorOrg(factorId: Long, orgId: Long): Object{
        return answerRepo.getAllByFactorOrg(factorId, orgId)
    }

    fun getAllDataByOrg(orgId: Long): List<Object>{
        return answerRepo.getAllByOrgId(orgId)
    }

//    fun getAllDataByOrgNew(orgId: Long){
//        var objList = answerRepo.getAllByOrgId(orgId)
//        for (obj:Answer in objList){
//            var clazz: Class<*> = obj.getClass()
//            var field: Field = clazz.getField("fieldName")
//            var fieldValue: Object = field.get(obj) as Object
//            println(fieldValue)
//        }
//    }





    fun addNewAnswer(answer: Answer?): Answer? {
        if (answer == null) return null
        return answerRepo.save(answer)
    }

    fun deleteAnswer(answerId: Long) {
        val exists = answerRepo.existsById(answerId)
        if (!exists) {
            throw IllegalArgumentException("student with id" + answerId + "does not exist")
        }
        answerRepo.deleteById(answerId)
    }


//    fun assignMarktoAnswer(answerId:Long, markId:Long):Answer?{
//        val answer = answerRepo.findById(answerId).get()
//        val mark = questionRepo.findById(markId).get()
//        answer.question = mark
//        mark.answers = (mark.answers as MutableSet<Answer>?: HashSet()).apply { add(answer) }
//        //markRepo.save(mark)
//        return  answerRepo.save(answer)
//        }

    fun assignOrgToAnswer(answerId: Long, orgId:Long): Answer?{
        val answer = answerRepo.findById(answerId).get()
        val org = orgRepo.findById(orgId).get()
        answer.organization = org
        org.answers = (org.answers as MutableSet<Answer>?: HashSet()).apply { add(answer) }
        //orgRepo.save(org)
        return answerRepo.save(answer)
    }
}