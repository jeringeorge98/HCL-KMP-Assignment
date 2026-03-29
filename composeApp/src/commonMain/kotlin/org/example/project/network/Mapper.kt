package org.example.project.network

import org.example.project.domain.CountryDetail
import org.example.project.network.models.CountryDetailsResponse


fun CountryDetailsResponse.toDomain(): CountryDetail =
    CountryDetail(
        name = this.name.official,
        officialName = this.name.official,
        capital = this.capitalInfo?.latlng,
        continent = this.continents.get(0),
        population = this.population,
        area = this.area,
        flag = this.flag,
        latlng = this.latlng,
        currencies = this.currencies,
        languages = this.languages ,
        borders = this.borders,
        capitalInfo = this.capitalInfo?.latlng,
    )