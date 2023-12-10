package com.example.notetask.models

import kotlinx.serialization.Serializable

@Serializable
data class Geometry (val coordinates : List<List<Double>>)
@Serializable
data class Feature(val geometry : Geometry)
@Serializable
data class GeoJSONDirection(
    val type: String,
    val features: List<Feature>
)


