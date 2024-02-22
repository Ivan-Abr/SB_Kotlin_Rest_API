package com.example.webApp.repository

import com.example.webApp.entity.Layer
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.Optional

interface LayerRepo: JpaRepository<Layer,Long> {
    @Query("SELECT l from Layer l WHERE l.layerName = ?1")
    fun findLayerByName(name:String?):Optional<Layer?>?
}