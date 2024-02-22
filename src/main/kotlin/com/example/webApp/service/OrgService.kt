package com.example.webApp.service

import com.example.webApp.entity.Answer
import com.example.webApp.entity.Organization
import com.example.webApp.repository.AnswerRepo
import com.example.webApp.repository.OrgRepo
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import java.util.*

@Service
class OrgService(private var orgRepo: OrgRepo,
    private  var answerRepo: AnswerRepo) {
    fun getOrgs(): List<Organization> {
        return orgRepo.findAll() as List<Organization>
    }

    fun getOrgById(book_id: Long): Optional<Organization> {
        var exist: Boolean = orgRepo.existsById(book_id)
        if (!exist)
            throw IllegalStateException("Organization with id: $book_id does not exist!")
        return orgRepo.findById(book_id)
    }

    fun addNewOrg(organization: Organization?): Organization? {
        if (organization == null) return null
        val orgOptional = orgRepo
            .findOrganizationByName(organization.orgName)
        check(!orgOptional!!.isPresent) { "name taken" }
        return orgRepo.save(organization)
    }

    fun deleteOrg(orgId: Long) {
        val exists = orgRepo.existsById(orgId)
        if (!exists) {
            throw IllegalArgumentException("student with id" + orgId + "does not exist")
        }
        orgRepo.deleteById(orgId)
    }

    @Transactional
    fun updateOrg(orgId: Long, orgName: String, annot: String): Organization? {
        val organization = orgRepo.findById(orgId)
            .orElseThrow { java.lang.IllegalStateException("student with id" + orgId + "does not exist") }
        if (orgName != null && orgName.length > 0 && organization.orgName != orgName) {
            organization.orgName = orgName
        }
        organization.orgAnnot = annot
        return organization
    }

    @Transactional
    fun addAnswersToOrganization(orgId: Long, answerId : Long): Organization{
        val organization = orgRepo.findById(orgId).get()
        val answer  =  answerRepo.findById(answerId).get()
        organization.answers = (organization.answers as MutableSet<Answer>).apply { add(answer) }
        return orgRepo.save(organization)
    }
}