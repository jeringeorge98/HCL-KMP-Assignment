package org.example.project.ui

import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import org.example.project.viewmodels.HomeViewModel

@Composable
fun DetailScreen(
    viewModel: HomeViewModel,
    countryName: String
) {

    Scaffold() {
        Text("Detail Screen with $countryName")
    }
}