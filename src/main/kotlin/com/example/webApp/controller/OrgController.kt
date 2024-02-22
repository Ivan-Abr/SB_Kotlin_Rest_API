package com.example.webApp.controller

import com.example.webApp.entity.Answer
import com.example.webApp.entity.Layer
import com.example.webApp.entity.Organization
import com.example.webApp.service.OrgService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.media.ArraySchema
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.web.bind.annotation.*
import java.util.*



@CrossOrigin(origins = ["http://localhost:3000"])
@RestController
@RequestMapping("dm/v1/org")
class OrgController (private var orgService: OrgService){


    @Operation(summary = "Выбор всех существующих организаций")
    @ApiResponses(
        ApiResponse(responseCode = "200", description = "Организации найдены", content = [Content(mediaType = "application/json",
            array = ArraySchema(schema = Schema(implementation = Layer::class))
        )]),
        ApiResponse(responseCode = "404", description = "Организации не найдены", content = [Content()]),
    )
    @GetMapping
    fun getMarks(): List<Organization> {
        return orgService.getOrgs()
    }



    @Operation(summary = "Выбор организации по его номеру")
    @ApiResponses(
        ApiResponse(responseCode = "200", description = "Организация найдена", content = [Content(mediaType = "application/json",
            array = ArraySchema(schema = Schema(implementation = Answer::class)))]),
        ApiResponse(responseCode = "400",  description =  "Введен неверный номер", content = [Content()]),
        ApiResponse(responseCode = "404", description = "Организация не найдена", content = [Content()]),)
    @GetMapping(path = ["{orgId}"])
    fun getMarkById(@PathVariable("orgId") orgId: Long): Optional<Organization> {
        return orgService.getOrgById(orgId)
    }

    @Operation(summary = "Создание новой организации")
    @PostMapping
    fun registerNewMark(@Parameter(description = "объект  для добавления ",
        schema = Schema(implementation = Answer::class))
        @RequestBody book: Organization) {
        orgService.addNewOrg(book)
    }

    @Operation(summary = "Удаление существующей организации по его номеру")
    @DeleteMapping(path = ["{orgId}"])
    fun deleteMark(@Parameter(description = "номер для поиска организации")
        @PathVariable("orgId") orgId: Long?) {
        orgService.deleteOrg(orgId!!)
    }

    @Operation(summary = "Изменение существующей организации по её номеру")
    @PutMapping(path = ["{orgId}"])
    fun updateMark(
        @Parameter(description = "номер для поиска организации")
        @PathVariable("orgId") orgId: Long,
        @Parameter(description = "новое имя")
        @RequestParam(required = false) orgName: String,
        @Parameter(description = "новое описание")
        @RequestParam(required = false) orgAnnot: String
    ){
        orgService.updateOrg(orgId,orgName,orgAnnot)
    }


    @Operation(summary = "Присоединение ответа к организации")
    @PutMapping(path = ["{orgId}/mark/{answerId}"])
    fun assignAnswertoOrg(
        @Parameter(description = "номер для поиска организации")
        @PathVariable orgId: Long,
        @Parameter(description = "номер для поиска ответа")
        @PathVariable answerId: Long
    ):Organization{return orgService.addAnswersToOrganization(orgId,answerId)}

}