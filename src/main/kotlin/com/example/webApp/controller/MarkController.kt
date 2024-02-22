package com.example.webApp.controller

import com.example.webApp.entity.Answer
import com.example.webApp.entity.Mark
import com.example.webApp.service.MarkService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.media.ArraySchema
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import java.util.*

@Controller
@CrossOrigin(origins = ["http://localhost:3000"])
@RequestMapping("dm/v1/mark")
class MarkController(private var markService: MarkService) {
    @Operation(summary = "Выбор всех существующих оценок")
    @ApiResponses(
        ApiResponse(responseCode = "200", description = "Оценка найдена", content = [Content(mediaType = "application/json",
            array = ArraySchema(schema = Schema(implementation = Mark::class))
        )]),
        ApiResponse(responseCode = "404", description = "Оценка не найдена", content = [Content()]),
    )
    @GetMapping
    fun getMarks(): List<Mark>{
        return markService.getAllMarks()
    }


    @Operation(summary = "Выбор оценки по его номеру")
    @ApiResponses(
        ApiResponse(responseCode = "200", description = "Оценка найдена", content = [Content(mediaType = "application/json",
            array = ArraySchema(schema = Schema(implementation = Answer::class))
        )]),
        ApiResponse(responseCode = "400",  description =  "Введен неверный номер", content = [Content()]),
        ApiResponse(responseCode = "404", description = "Оценка не найдена", content = [Content()]),
    )
    @GetMapping(path = ["{markId}"])
    fun getMarkById(@PathVariable("markId") markId: Long): Optional<Mark> {
        return markService.getMarkById(markId)
    }


    @Operation(summary = "Создание нового оценки")
    @PostMapping
    fun regNewMark(@Parameter(description = "объект  для добавления ",
        schema = Schema(implementation = Answer::class)
    ) @RequestBody mark: Mark
    ){
        markService.postMark(mark)
    }

    @Operation(summary = "Удаление существующего оценки по его номеру")
    @DeleteMapping(path = ["{markId}"])
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteStudent(@Parameter(description = "номер для поиска оценки") @PathVariable("markId") markId: Long?) {
        markService.deleteMark(markId!!)
    }

    @Operation(summary = "Изменение имени существующего оценки по его номеру")
    @PutMapping(path = ["{markId}"])
    fun updateMark(
        @Parameter(description = "номер для поиска оценки")
        @PathVariable("markId") markId: Long,
        @Parameter(description = "новое название")
        @RequestParam(required = false) markName: String,
        @Parameter(description = "новое значение")
        @RequestParam(required = false) markValue: Int
    ){
        markService.updateMark(markId,markName, markValue)
    }

    @Operation(summary = "Присоединение показателя к слою")
    @PutMapping(path = ["{markId}/answer/{answerId}"])
    fun assignAnswertoMark(
        @Parameter(description = "номер для поиска оценки")
        @PathVariable markId: Long,
        @Parameter(description = "номер для поиска ответа")
        @PathVariable answerId: Long
    ): Mark?{return markService.addAnswersToMark(markId,markId)}
}