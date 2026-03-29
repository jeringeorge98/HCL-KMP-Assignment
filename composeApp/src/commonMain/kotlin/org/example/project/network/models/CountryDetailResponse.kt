package org.example.project.network.models

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

@Serializable
data class CountryDetailsResponse(
    val altSpellings: List<String>?=null,
    val area: Double,
    val borders: List<String>?=null,
    val capitalInfo: CapitalInfo?=null,
    val capital:List<String>?=null,
    val car: JsonObject?=null,
    val cca2: String?="",
    val cca3: String?="",
    val ccn3: String?="",
    val coatOfArms: Map<String, String>?=null,
    val continents: List<String>,
    val currencies: Map<String, Currencies>? = null,
    val demonyms: JsonObject? = null,
    val fifa: String?=null,
    val flag: String?,
    val flags: JsonObject?=null,
    val idd: JsonObject?=null,
    val independent: Boolean,
    val landlocked: Boolean,
    val languages: Map<String, String?>?=null,
    val latlng: List<Double>,
    val maps: Maps?=null,
    val name: Name,
    val population: Int=0,
    val postalCode: JsonObject?=null,
    val region: String?="",
    val startOfWeek: String?="",
    val status: String?="",
    val subregion: String?="",
    val timezones: List<String>?=null,
    val tld: List<String>?=null,
    val translations: JsonObject?=null,
    val unMember: Boolean?=null
)