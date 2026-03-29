package org.example.project.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import org.example.project.domain.CountryDetail
import org.example.project.viewmodels.HomeViewModel
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    viewModel: HomeViewModel,
    countryName: String
) {
    val state by viewModel.uiState.collectAsState()
    LaunchedEffect(Unit){
        viewModel.getCountryByName(countryName)
    }

    Scaffold(
        topBar = { CenterAlignedTopAppBar(
            title = {Text("Details Screen", style = MaterialTheme.typography.titleMedium) },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary,
                titleContentColor = MaterialTheme.colorScheme.onPrimary,
                scrolledContainerColor = Color.Unspecified,
                navigationIconContentColor = Color.Unspecified,
                actionIconContentColor = Color.Unspecified,
                subtitleContentColor = Color.Unspecified,
            ),
            windowInsets = WindowInsets.statusBars

        ) }
    ) {paddingValues ->
        Column(modifier = Modifier.fillMaxSize().padding(paddingValues).padding(12.dp)) {
            when(state) {
                is HomeViewModel.UiState.Error -> NoDataView()
                is HomeViewModel.UiState.Loading -> CircularProgressIndicator()
                is HomeViewModel.UiState.Success ->{
                    if((state as HomeViewModel.UiState.Success).countries.isEmpty()){
                        NoDataView()
                    }else{
                        CountryDetailView((state as HomeViewModel.UiState.Success).countries.first())
                    }
                }
                is HomeViewModel.UiState.Empty -> {}
            }
        }
    }
}

@Composable
fun NoDataView() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        Text("No Data to display")
    }
}

@Composable
fun CountryDetailView(detail: CountryDetail){

    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        //Header Section
        item{
            Column(
                horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = detail.flag ?: "N/A",
                    style = MaterialTheme.typography.displayLarge
                )
                Text(
                    text = detail.officialName,
                    style = MaterialTheme.typography.headlineMedium,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = detail.continent,
                    style = MaterialTheme.typography.labelMedium,
                    textAlign = TextAlign.Center
                )
            }
        }
        // Geography Info Card
        item {
            DetailCard(
                title = "Geography"
            ){
                InfoRow("Area","${detail.area} km²")
                InfoRow("Borders",detail.borders?.joinToString(", ") ?:"N/A")
                InfoRow("Capital",detail.capital?:"N/A")
            }
        }
        item {
            DetailCard(
                title = "Demographics & Culture"
            ){
                InfoRow("Population",detail.population.toString())

                InfoRow("Languages",detail.languages?.values?.joinToString(", ") ?:"N/A")

                InfoRow("Currencies",detail.currencies?.values?.joinToString(", "){it.name.toString()}?:"N/A")
            }
        }
        item {
            DetailCard(
                title = "Other Info"
            ){
               Row {
                   Text("Coat of Arms:")
                   Spacer(modifier = Modifier.width(5.dp))
                   detail.coatOfArms.let { imageUrl ->
                       AsyncImage(
                           model = imageUrl?.get("png"),
                           contentDescription = "Coat of Arms",
                           modifier = Modifier.size(50.dp),
                           contentScale = ContentScale.Fit,
                           alignment = Alignment.Center,
                       )
                   }
               }
            }
        }

    }

}

@Composable
fun DetailCard(title: String, content: @Composable ColumnScope.() -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            HorizontalDivider(modifier = Modifier.padding(bottom = 8.dp))
            content()
        }
    }
}
@Composable
fun InfoRow(label: String, value: String, icon: String? = null) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (icon != null) {
            Text(icon, modifier = Modifier.padding(end = 8.dp))
        }
        Text(
            text = "$label: ",
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.secondary
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}