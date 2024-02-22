package com.example.webApp.controller

import com.example.webApp.entity.Answer
import com.example.webApp.entity.Layer
import com.example.webApp.entity.Question
import com.example.webApp.service.QuestionService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.media.ArraySchema
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("dm/v1/question")
class QuestionController(private var questionService: QuestionService) {

    @Operation(summary = "Выбор всех существующих показателей")
    @ApiResponses(
        ApiResponse(responseCode = "200", description = "Показатели найдены", content = [Content(mediaType = "application/json",
            array = ArraySchema(schema = Schema(implementation = Layer::class))
        )]),
        ApiResponse(responseCode = "404", description = "Показатели не найдены", content = [Content()]),
    )
    @GetMapping
    fun getQuestions(): List<Question> {
        return questionService.getQuestions()
    }

    @Operation(summary = "Выбор показателя по его номеру")
    @ApiResponses(
        ApiResponse(responseCode = "200", description = "Показатель найден", content = [Content(mediaType = "application/json",
            array = ArraySchema(schema = Schema(implementation = Answer::class)))]),
        ApiResponse(responseCode = "400",  description =  "Введен неверный номер", content = [Content()]),
        ApiResponse(responseCode = "404", description = "Показатель не найден", content = [Content()]),)
    @GetMapping(path = ["{questionId}"])
    fun getQuestionById(@PathVariable("questionId") questionId: Long): Optional<Question> {
        return questionService.getQuestionById(questionId)
    }


    @Operation(summary = "Создание нового показателя")
    @PostMapping
    fun registerNewQuestion(@Parameter(description = "объект  для добавления ",
        schema = Schema(implementation = Answer::class))
        @RequestBody book: Question) {
        questionService.addNewQuestion(book)
    }

    @Operation(summary = "Удаление существующего показателя по его номеру")
    @DeleteMapping(path = ["{questionId}"])
    fun deleteQuestion(@Parameter(description = "номер для поиска показателя")
        @PathVariable("questionId") questionId: Long?) {
        questionService.deleteQuestion(questionId!!)
    }

    @Operation(summary = "Изменение существующего показателя по его номеру")
    @PutMapping(path = ["{questionId}"])
    fun updateQuestion(
        @Parameter(description = "номер для поиска показателя")
        @PathVariable("questionId") questionId: Long,
        @Parameter(description = "новое имя")
        @RequestParam(required = false) questionName: String,
        @Parameter(description = "новое описание")
        @RequestParam(required = false) questionAnnot: String
    ){
        questionService.updateQuestion(questionId,questionName,questionAnnot)
    }

    @Operation(summary = "Присоединение оценки к показателю")
    @PutMapping(path = ["{questionId}/mark/{markId}"])
    fun assignAnswertoOrg(
        @Parameter(description = "номер для поиска показателя")
        @PathVariable questionId: Long,
        @Parameter(description = "номер для поиска ответа")
        @PathVariable markId: Long
    ): Question? {return questionService.addMarkToQuestion(questionId,markId)}

    
}