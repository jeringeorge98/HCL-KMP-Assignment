package org.example.project.network.models

import kotlinx.serialization.Serializable

@Serializable
data class Currencies(
    val symbol: String?=null,
    val name: String?=null

)
