package org.example.project.network.repository

import org.example.project.domain.CountryDetail
import org.example.project.network.NetworkResponse

interface CountryRepository {
    suspend fun getCountriesByName(name: String): NetworkResponse<List<CountryDetail>>
}