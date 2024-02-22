package com.example.webApp.controller

import com.example.webApp.entity.Answer
import com.example.webApp.entity.Factor
import com.example.webApp.excel.ExcelExporter
import com.example.webApp.service.FactorService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.media.ArraySchema
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.io.IOException
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

@RestController
@CrossOrigin(origins = ["http://localhost:3000"])
@RequestMapping("dm/v1/factor")
class FactorController(private var factorService: FactorService) {


    @Operation(summary = "Выбор всех существующих факторов")
    @ApiResponses(
        ApiResponse(responseCode = "200", description = "Фактор найден", content = [Content(mediaType = "application/json",
            array = ArraySchema(schema = Schema(implementation = Factor::class))
        )]),
        ApiResponse(responseCode = "404", description = "Фактор не найден", content = [Content()]),
    )
    @GetMapping
    fun getFactors(): List<Factor>{
        return factorService.getFactors()
    }

//    @GetMapping(path = ["export/excel"])
//    @Throws(IOException::class)
//    fun exportToExcel(response: HttpServletResponse){
//        response.contentType = "application/octet-stream"
//        var dateFormatter: DateFormat = SimpleDateFormat("yyyy-MM-dd_HH:mm:ss")
//        var currentDateTime: String = dateFormatter.format(Date())
//
//        var headerKey: String = "Content-Disposition"
//        var headerValue: String = "attachment; filename=users_"+currentDateTime+".xlsx"
//        response.setHeader(headerKey, headerValue)
//        var listFactors: List<Factor> = factorService.getFactors()
//        var excelExporter = ExcelExporter(listFactors)
//        excelExporter.export(response)
//    }

    @Operation(summary = "Выбор фактора по его номеру")
    @ApiResponses(
        ApiResponse(responseCode = "200", description = "Фактор найден", content = [Content(mediaType = "application/json",
            array = ArraySchema(schema = Schema(implementation = Answer::class)))]),
        ApiResponse(responseCode = "400",  description =  "Введен неверный номер", content = [Content()]),
        ApiResponse(responseCode = "404", description = "Фактор не найден", content = [Content()]),
    )
    @GetMapping(path = ["{factorId}"])
    fun getFactorById(@PathVariable("factorId") factorId: Long): Optional<Factor> {
        return factorService.getFactorById(factorId)
    }


    @Operation(summary = "Создание нового фактора")
    @PostMapping
    fun regNewFactor(@Parameter(description = "объект  для добавления ",
        schema = Schema(implementation = Answer::class)) @RequestBody factor: Factor){
        factorService.addFactor(factor)
    }

    @Operation(summary = "Удаление существующего фактора по его номеру")
    @DeleteMapping(path = ["{factorId}"])
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteStudent(@Parameter(description = "номер для поиска фактора") @PathVariable("factorId") factorId: Long?) {
        factorService.deleteFactor(factorId!!)
    }

    @Operation(summary = "Изменение имени существующего фактора по его номеру")
    @PutMapping(path = ["{factorId}"])
    fun updateFactor(
        @Parameter(description = "номер для поиска фактора")
        @PathVariable("factorId") factorId: Long,
        @Parameter(description = "новое название")
        @RequestParam(required = false) factorName: String
    ){
        factorService.updateFactor(factorId,factorName)
    }

    @Operation(summary = "Присоединение показателя к фактору")
    @PutMapping(path = ["{factorId}/question/{questionId}"])
    fun assignMarktoFactor(
        @Parameter(description = "номер для поиска фактора")
        @PathVariable factorId: Long,
        @Parameter(description = "номер для поиска показателя")
        @PathVariable questionId: Long
    ):Factor?{return factorService.assignMarksToFactor(factorId,questionId)}



}