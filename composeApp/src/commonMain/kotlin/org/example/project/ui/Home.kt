package org.example.project.ui

import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import org.example.project.network.KtorClientProvider
import org.example.project.network.repository.CountryRepositoryImpl

@Composable
fun Home(){
    val repository = CountryRepositoryImpl(KtorClientProvider)
    LaunchedEffect(Unit){

    repository.getCountriesByName("ind")

    }

    Scaffold(
        modifier = Modifier
    ) {paddingValues ->
        Text("Home Screen")
    }
}