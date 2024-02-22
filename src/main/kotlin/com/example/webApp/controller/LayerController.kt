package com.example.webApp.controller

import com.example.webApp.entity.Answer
import com.example.webApp.entity.Layer
import com.example.webApp.service.LayerService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.media.ArraySchema
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@CrossOrigin(origins = ["http://localhost:3000"])
@RequestMapping("dm/v1/layer")
class LayerController(private var layerService: LayerService) {


    @Operation(summary = "Выбор всех существующих слоёв")
    @ApiResponses(
        ApiResponse(responseCode = "200", description = "Слой найден", content = [Content(mediaType = "application/json",
            array = ArraySchema(schema = Schema(implementation = Layer::class))
        )]),
        ApiResponse(responseCode = "404", description = "Слой не найден", content = [Content()]),
    )
    @GetMapping
    fun getLayers(): List<Layer>{
        return layerService.getLayers()
    }


    @Operation(summary = "Выбор слоя по его номеру")
    @ApiResponses(
        ApiResponse(responseCode = "200", description = "Слой найден", content = [Content(mediaType = "application/json",
            array = ArraySchema(schema = Schema(implementation = Answer::class)))]),
        ApiResponse(responseCode = "400",  description =  "Введен неверный номер", content = [Content()]),
        ApiResponse(responseCode = "404", description = "Слой не найден", content = [Content()]),
    )
    @GetMapping(path = ["{layerId}"])
    fun getLayerById(@PathVariable("layerId") layerId: Long): Optional<Layer> {
        return layerService.getLayerById(layerId)
    }


    @Operation(summary = "Создание нового слоя")
    @PostMapping
    fun regNewLayer(@Parameter(description = "объект  для добавления ",
    schema = Schema(implementation = Answer::class)) @RequestBody layer: Layer){
        layerService.addLayer(layer)
    }

    @Operation(summary = "Удаление существующего слоя по его номеру")
    @DeleteMapping(path = ["{layerId}"])
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteStudent(@Parameter(description = "номер для поиска слоя") @PathVariable("layerId") layerId: Long?) {
        layerService.deleteLayer(layerId!!)
    }

    @Operation(summary = "Изменение имени существующего слоя по его номеру")
    @PutMapping(path = ["{layerId}"])
    fun updateLayer(
        @Parameter(description = "номер для поиска слоя")
        @PathVariable("layerId") layerId: Long,
        @Parameter(description = "новое название")
        @RequestParam(required = false) layerName: String
    ){
        layerService.updateLayer(layerId,layerName)
    }

    @Operation(summary = "Присоединение показателя к слою")
    @PutMapping(path = ["{layerId}/mark/{markId}"])
    fun assignMarktoLayer(
        @Parameter(description = "номер для поиска слоя")
        @PathVariable layerId: Long,
        @Parameter(description = "номер для поиска показателя")
        @PathVariable markId: Long
    ):Layer?{return layerService.assignMarksToLayer(layerId,markId)}



}