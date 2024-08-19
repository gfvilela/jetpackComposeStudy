package br.dev.gustavovilela.firstprojectcompose.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import br.dev.gustavovilela.firstprojectcompose.data.mock.MockCountryRepository
import br.dev.gustavovilela.firstprojectcompose.domain.usecase.GetCountriesUseCase
import br.dev.gustavovilela.firstprojectcompose.presentation.component.CustomAppBar
import br.dev.gustavovilela.firstprojectcompose.presentation.theme.FirstProjectComposeTheme
import br.dev.gustavovilela.firstprojectcompose.presentation.ui.demo.DemoScreen
import br.dev.gustavovilela.firstprojectcompose.presentation.ui.list.CountryListScreen
import br.dev.gustavovilela.firstprojectcompose.presentation.ui.list.CountryListViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FirstProjectComposeTheme {
                FirstScreen()
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun FirstScreen() {
        val mock = MockCountryRepository()
        val usecase = GetCountriesUseCase(repository = mock)
        val viewmodel = CountryListViewModel(getCountriesUseCase = usecase)

        val currentContext = LocalContext.current
        Scaffold(
            topBar = {
                CustomAppBar()
            },
            content = { padding ->
                Surface(
                    modifier = Modifier.padding(padding),
                    color = MaterialTheme.colorScheme.background
                ) {
//                    DemoScreen()
                    CountryListScreen(viewModel = viewmodel)
                }
            }
        )
    }

    @Preview
    @Composable
    fun FistScreenPreview() {
        FirstScreen()
    }
}