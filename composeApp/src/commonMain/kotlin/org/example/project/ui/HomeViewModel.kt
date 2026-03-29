package org.example.project.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.example.project.network.repository.CountryRepository

class HomeViewModel(private val repository: CountryRepository): ViewModel() {

    fun getCountryByName(countryName: String){
        viewModelScope.launch {
            repository.getCountriesByName(countryName)
        }
    }

}