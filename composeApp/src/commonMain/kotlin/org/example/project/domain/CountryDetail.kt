package org.example.project.domain

import org.example.project.network.models.Currencies

data class CountryDetail(
    val name: String,
    val officialName: String,
    val capital: List<Double>?,
    val continent: String,
    val borders: List<String>?,
    val population: Int,
    val area: Double,
    val flag: String?,
    val languages: Map<String, String?>?,
    val currencies: Map<String, Currencies>?,
    val latlng: List<Double>,
    val capitalInfo: List<Double>? =null
)