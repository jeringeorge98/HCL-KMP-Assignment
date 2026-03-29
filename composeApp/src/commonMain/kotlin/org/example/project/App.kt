package org.example.project

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import org.jetbrains.compose.resources.painterResource

import hclassignment.composeapp.generated.resources.Res
import hclassignment.composeapp.generated.resources.compose_multiplatform
import org.example.project.network.KtorClientProvider
import org.example.project.network.repository.CountryRepositoryImpl
import org.example.project.ui.Home
import org.example.project.viewmodels.HomeVMFactory

@Composable
@Preview
fun App() {
    MaterialTheme {
        Home(
            viewModel = viewModel(
                factory = HomeVMFactory(
                    repository = CountryRepositoryImpl(
                        apiService = KtorClientProvider
                    )
                )
            )
        )
    }
}