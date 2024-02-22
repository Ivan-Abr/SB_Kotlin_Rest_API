package com.example.webApp.repository

import com.example.webApp.entity.AnsData
import com.example.webApp.entity.Answer
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType

@Repository
interface AnswerRepo: JpaRepository<Answer, Long> {
    @Query(
        "SELECT  avg(a.question.markValue), l.layerName, org.orgName, mlst.year  from Answer AS a " +
                "   LEFT JOIN Mark AS m ON a.question.markId = m.markId" +
                "   LEFT JOIN Question AS q ON m.question.questionId = q.questionId" +
                "   LEFT JOIN Layer AS l ON q.layer.layerId = l.layerId" +
                "   LEFT JOIN Organization as org On a.organization.orgId = org.orgId" +
                "   LEFT JOIN Milestone AS mlst ON a.milestone.milestoneId = mlst.milestoneId" +
                "   WHERE l.layerId = ?1 and org.orgId = ?2" +
                "   GROUP BY l.layerName, org.orgName, mlst.year")
    fun getAllByLayerOrg(layerId: Long, orgId: Long): Object

    @Query(
        "SELECT  avg(a.question.markValue), f.factorShortName, org.orgName, mlst.year  from Answer AS a " +
                "   LEFT JOIN Mark AS m ON a.question.markId = m.markId" +
                "   LEFT JOIN Question AS q ON m.question.questionId = q.questionId" +
                "   LEFT JOIN Factor AS f ON q.factor.factorId = f.factorId" +
                "   LEFT JOIN Organization as org On a.organization.orgId = org.orgId" +
                "   LEFT JOIN Milestone AS mlst ON a.milestone.milestoneId = mlst.milestoneId" +
                "   WHERE f.factorId = ?1 and org.orgId = ?2" +
                "   GROUP BY f.factorShortName, org.orgName,mlst.year")
    fun getAllByFactorOrg(factorId: Long, orgId: Long): Object

    @Query(
        "SELECT  avg(a.question.markValue), l.layerName, f.factorShortName, org.orgName, mlst.year  from Answer AS a " +
                "   LEFT JOIN Mark AS m ON a.question.markId = m.markId" +
                "   LEFT JOIN Question AS q ON m.question.questionId = q.questionId" +
                "   LEFT JOIN Factor AS f ON q.factor.factorId = f.factorId" +
                "   LEFT JOIN Layer AS l ON q.layer.layerId = l.layerId" +
                "   LEFT JOIN Organization as org On a.organization.orgId = org.orgId" +
                "   LEFT JOIN Milestone AS mlst ON a.milestone.milestoneId = mlst.milestoneId" +
                "   WHERE org.orgId = ?1" +
                "   GROUP BY l.layerName, org.orgName, f.factorShortName, mlst.year")
    fun getAllByOrgId(orgId: Long): List<Object>
}
//@Repository
//interface AnsDataRepo: JpaRepository<AnsData, Long>{
//    @Query(
//        "SELECT  avg(a.question.markValue), l.layerName, f.factorShortName, org.orgName, mlst.year  from Answer AS a " +
//                "   LEFT JOIN Mark AS m ON a.question.markId = m.markId" +
//                "   LEFT JOIN Question AS q ON m.question.questionId = q.questionId" +
//                "   LEFT JOIN Factor AS f ON q.factor.factorId = f.factorId" +
//                "   LEFT JOIN Layer AS l ON q.layer.layerId = l.layerId" +
//                "   LEFT JOIN Organization as org On a.organization.orgId = org.orgId" +
//                "   LEFT JOIN Milestone AS mlst ON a.milestone.milestoneId = mlst.milestoneId" +
//                "   WHERE org.orgId = ?1" +
//                "   GROUP BY l.layerName, org.orgName, f.factorShortName, mlst.year")
//    fun getAllByOrgId(orgId: Long): List<Object>
//}