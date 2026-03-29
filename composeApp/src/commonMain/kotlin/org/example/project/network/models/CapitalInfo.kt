package org.example.project.network.models

import kotlinx.serialization.Serializable

@Serializable
data class CapitalInfo(
    val latlng: List<Double>?=null
)
