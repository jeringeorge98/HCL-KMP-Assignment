package org.example.project.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.example.project.domain.CountryDetail
import org.example.project.navigation.NavCoordinator
import org.example.project.network.NetworkResponse
import org.example.project.network.repository.CountryRepository

class HomeViewModel(private val repository: CountryRepository,private val navCoordinator: NavCoordinator): ViewModel() {

    private var _uiState = MutableStateFlow<UiState>(UiState.Empty)
    val uiState = _uiState.asStateFlow()
    sealed class UiState{
        object Loading: UiState()
        data class Success(val countries: List<CountryDetail>): UiState()
        data class Error(val message: String): UiState()
        object Empty: UiState()

    }
    fun getCountryByName(countryName: String){
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            val response = repository.getCountriesByName(countryName)
            when(response){
                is NetworkResponse.Error<*> ->_uiState.value =  UiState.Error(response.message.toString())
                is NetworkResponse.Loading ->_uiState.value = UiState.Loading
                is NetworkResponse.Success ->_uiState.value = UiState.Success(response.data)
            }
        }
    }
    fun navigateToDetailScreen(countryName: String){
        navCoordinator.toDetailScreen(countryName)
    }

}