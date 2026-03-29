package org.example.project.network.models

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

@Serializable
data class Name(
    val common: String,
    val nativeName: Map<String, JsonObject>? =null,
    val official: String
)