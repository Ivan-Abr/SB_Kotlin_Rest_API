package com.example.webApp.controller

import com.example.webApp.entity.Answer
import com.example.webApp.entity.Layer
import com.example.webApp.entity.Milestone
import com.example.webApp.service.MilestoneService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.media.ArraySchema
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.web.bind.annotation.*
import java.time.LocalDate
import java.util.Optional


@RestController
@RequestMapping("dm/v1/milestone")
class MilestoneController (private var milestoneService: MilestoneService){


    @Operation(summary = "Выбор всех существующих этапов")
    @ApiResponses(
        ApiResponse(responseCode = "200", description = "Этапы найдены", content = [Content(mediaType = "application/json",
            array = ArraySchema(schema = Schema(implementation = Layer::class))
        )]),
        ApiResponse(responseCode = "404", description = "Этапы не найдены", content = [Content()]),
    )
    @GetMapping
    fun getMileSt(): List<Milestone>{
        return milestoneService.getMileSt()
    }

    @Operation(summary = "Выбор этапа по его номеру")
    @ApiResponses(
        ApiResponse(responseCode = "200", description = "Этап найден", content = [Content(mediaType = "application/json",
            array = ArraySchema(schema = Schema(implementation = Answer::class)))]),
        ApiResponse(responseCode = "400",  description =  "Введен неверный номер", content = [Content()]),
        ApiResponse(responseCode = "404", description = "Этап не найден", content = [Content()]),)
    @GetMapping(path = ["{milestoneId}"])
    fun getMileStById(@PathVariable("milestoneId") milestoneId: Long): Optional<Milestone>{
        return milestoneService.getMileStById(milestoneId)
    }

    @Operation(summary = "Создание нового этапа")
    @PostMapping
    fun registerNewMileSt(@Parameter(description = "объект  для добавления ",
        schema = Schema(implementation = Answer::class))
                          @RequestBody milestone: Milestone) {
        milestoneService.addNewMileSt(milestone)
    }

    @Operation(summary = "Удаление существующего этапа по его номеру")
    @DeleteMapping(path = ["{milestoneId}"])
    fun deleteMileSt(@Parameter(description = "номер для поиска этапа")
        @PathVariable("milestoneId") milestoneId: Long?) {
        milestoneService.deleteMileSt(milestoneId!!)
    }

    @Operation(summary = "Изменение существующего этапа по его номеру")
    @PutMapping(path = ["{milestoneId}"])
    fun updateMileSt(
        @Parameter(description = "номер для поиска этапа")
        @PathVariable("milestoneId") milestoneId: Long,
        @Parameter(description = "новая дата начала")
        @RequestParam(required = false) dateFrom: LocalDate,
        @Parameter(description = "новая дата окончания")
        @RequestParam(required = false) dateTo: LocalDate
    ){
        milestoneService.updateMileSt(milestoneId,dateFrom,dateTo)
    }

    @Operation(summary = "Присоединение ответа к этапу")
    @PutMapping(path = ["{milestoneId}/answer/{answerId}"])
    fun assignAnswerToMilestone(
        @Parameter(description = "номер для поиска этапа")
        @PathVariable milestoneId: Long,
        @Parameter(description = "номер для поиска ответа")
        @PathVariable answerId: Long
    ): Milestone?{return milestoneService.addAnswerToMilestone(milestoneId,answerId)}

}