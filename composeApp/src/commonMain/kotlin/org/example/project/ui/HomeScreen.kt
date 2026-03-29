package org.example.project.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.example.project.domain.CountryDetail
import org.example.project.viewmodels.HomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(viewModel: HomeViewModel){
    val state by viewModel.uiState.collectAsState()
    var searchQuery by remember { mutableStateOf("") }
    val topBarColor = Color(0xFF0DADB3) // Very light grey/white
    val contentColor = Color(0xFF1A1C1E)

    Scaffold(
        topBar = { CenterAlignedTopAppBar(
            title = {Text("Search for a Country", style = MaterialTheme.typography.titleMedium) },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary,
                titleContentColor = MaterialTheme.colorScheme.onPrimary,
                scrolledContainerColor = Color.Unspecified,
                navigationIconContentColor = Color.Unspecified,
                actionIconContentColor = Color.Unspecified,
                subtitleContentColor = Color.Unspecified,
            ),
            windowInsets = WindowInsets.statusBars

        ) },
        modifier = Modifier
    ) {paddingValues ->
        Column(
            modifier = Modifier.fillMaxSize().padding(paddingValues).padding(8.dp),

        ){
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = {searchQuery = it},
                    label = {Text("Search")},
                )
                Spacer(modifier = Modifier.width(5.dp))
                Button(onClick = {viewModel.getCountryByName(searchQuery)}){
                    Text("Search")
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Box(modifier = Modifier.fillMaxWidth().weight(1f), contentAlignment = Alignment.Center) {
                when (state) {
                    is HomeViewModel.UiState.Error -> ErrorView()
                    is HomeViewModel.UiState.Loading -> CircularProgressIndicator()
                    is HomeViewModel.UiState.Success -> CountryListView(
                        Modifier,
                        (state as HomeViewModel.UiState.Success).countries,
                        viewModel
                    )

                    is HomeViewModel.UiState.Empty -> {}

                }
            }
        }
    }
}

@Composable
fun CountryListView(modifier: Modifier = Modifier, items:List<CountryDetail>,viewModel: HomeViewModel){
    if(items.isEmpty()){
        Box(modifier.fillMaxSize(), contentAlignment = Alignment.Center){
            Text("No results found")
        }
        }
    else{
        LazyColumn(
            modifier=modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ){
           items(items){country ->
               Card(
                   modifier= modifier.fillMaxWidth(),
                   onClick = {viewModel.navigateToDetailScreen(country.officialName)}
               ){
                   Text(
                       text = country.officialName,
                       modifier= Modifier.padding(16.dp)
                   )
               }
           }
        }
    }
}

@Composable
fun ErrorView(){
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        Text("Error Response Recieveid", color = MaterialTheme.colorScheme.error)
    }
}
