package org.example.project.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import org.example.project.network.repository.CountryRepository
import kotlin.reflect.KClass

class HomeVMFactory(private val repository: CountryRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: KClass<T>, extras: CreationExtras): T {
        return HomeViewModel(
            repository = repository
        ) as T
    }
}