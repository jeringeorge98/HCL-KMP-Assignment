package org.example.project

import org.example.project.navigation.NavCoordinator
import org.example.project.network.KtorClientProvider
import org.example.project.network.repository.CountryRepositoryImpl

class ApplicationContainer {
    val navCoordinator by lazy { NavCoordinator() }
    val countryRepository by lazy { CountryRepositoryImpl(KtorClientProvider) }
}