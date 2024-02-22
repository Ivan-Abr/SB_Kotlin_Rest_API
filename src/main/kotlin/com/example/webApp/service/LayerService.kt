package com.example.webApp.service

import com.example.webApp.entity.Layer
import com.example.webApp.entity.Question
import com.example.webApp.repository.LayerRepo
import com.example.webApp.repository.QuestionRepo
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import java.util.Optional

@Service
class LayerService(
    private var layerRepo: LayerRepo,
    private var questionRepo: QuestionRepo
) {

    fun getLayers(): List<Layer>{
        return layerRepo.findAll()
    }

    fun getLayerById(layerId: Long): Optional<Layer>{
        var exist: Boolean = layerRepo.existsById(layerId)
        if(!exist)
            throw IllegalStateException("Layer with this ID does not exist!")
        return layerRepo.findById(layerId)
    }
    @Transactional
    fun addLayer(layer:Layer?): Layer?{
        if (layer == null) return null
        val layerOpt = layerRepo.findLayerByName(layer.layerName)
        check(!layerOpt!!.isPresent){"name taken"}
        return layerRepo.save(layer)
    }
    @Transactional
    fun deleteLayer(layerId: Long) {
        val exists = layerRepo.existsById(layerId)
        if (!exists) {
            throw IllegalArgumentException("layer with id" + layerId + "does not exist")
        }
        layerRepo.deleteById(layerId)
    }


    @Transactional
    fun updateLayer(layerId: Long, layerName: String): Layer? {
        val layer = layerRepo.findById(layerId)
            .orElseThrow { java.lang.IllegalStateException("layer with id" + layerId + "does not exist") }
        if (layerName != null && layerName.isNotEmpty() && layer.layerName != layerName) {
            layer.layerName = layerName
        }

        return layer
    }
    fun assignMarksToLayer(layerId: Long, markId: Long): Layer?{
        val layer = layerRepo.findById(layerId).get()
        val mark = questionRepo.findById(markId).get()

        layer.questions = (layer.questions as MutableSet<Question?>?:HashSet()).apply {
            add(mark)
        }
        return  layerRepo.save(layer)

    }




}