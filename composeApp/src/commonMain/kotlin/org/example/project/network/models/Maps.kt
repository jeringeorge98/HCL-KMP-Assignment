package org.example.project.network.models

import kotlinx.serialization.Serializable

@Serializable
data class Maps(
    val googleMaps: String,
    val openStreetMaps: String
)
