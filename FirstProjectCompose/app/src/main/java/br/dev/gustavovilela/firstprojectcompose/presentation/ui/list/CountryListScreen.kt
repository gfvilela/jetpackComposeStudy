package br.dev.gustavovilela.firstprojectcompose.presentation.ui.list

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import br.dev.gustavovilela.firstprojectcompose.data.mock.MockCountryRepository
import br.dev.gustavovilela.firstprojectcompose.domain.model.Country
import br.dev.gustavovilela.firstprojectcompose.domain.usecase.GetCountriesUseCase

@Composable
fun CountryListScreen(
    navController: NavHostController,
    viewModel: CountryListViewModel
) {
    val countries = viewModel.countries.collectAsState().value
    val isButtonVisisble by viewModel.isButtonVisible
    val selectedCountry by viewModel.selectedCountry
    val context: Context = LocalContext.current

    selectedCountry?.let { country ->
        Toast.makeText(context, "Clicked: ${country.name}", Toast.LENGTH_SHORT).show()
        viewModel.resetCountrySelected()
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        LazyColumn {
            items(countries) { country ->
                CountryListItem(country, onItemClick = viewModel::onCountryClick)
            }

            item {
                // Load more button to demonstrate infinite scrolling
                if (isButtonVisisble) {
                    Button(onClick = { viewModel.loadCountries() }) {
                        Text("Load More")
                    }
                }
            }
        }
    }
}

@Composable
fun CountryListItem(country: Country, onItemClick: (Country) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemClick(country) }
            .padding(16.dp)
    ) {
        Text(text = country.name, style = MaterialTheme.typography.titleLarge)
        Text(text = country.capital, style = MaterialTheme.typography.titleSmall)
    }
}

@Composable
@Preview(showBackground = true)
fun CountryListScreenPreview() {
    val mock = MockCountryRepository()
    val usecase = GetCountriesUseCase(repository = mock)
    val viewmodel = CountryListViewModel(getCountriesUseCase = usecase)
    CountryListScreen(rememberNavController(), viewModel = viewmodel)
}