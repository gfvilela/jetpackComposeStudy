package br.dev.gustavovilela.firstprojectcompose.presentation.ui.list

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.dev.gustavovilela.firstprojectcompose.domain.model.Country
import br.dev.gustavovilela.firstprojectcompose.domain.usecase.GetCountriesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CountryListViewModel(private val getCountriesUseCase: GetCountriesUseCase) : ViewModel() {
    private val _countries = MutableStateFlow<List<Country>>(emptyList())
    val countries: StateFlow<List<Country>> = _countries.asStateFlow()

    // State variable to control the visibility of the button
    private val _isButtonVisible = mutableStateOf(true)
    val isButtonVisible: State<Boolean> = _isButtonVisible

    // State variable to hold the selected country
    private var _selectedCountry by mutableStateOf<Country?>(null)
    val selectedCountry: State<Country?> get() = mutableStateOf(_selectedCountry)

    private var currentPage = 1
    private val pageSize = 3

    init {
        loadCountries()
    }

    fun loadCountries() {
        viewModelScope.launch {
            val newCountries = getCountriesUseCase.execute(currentPage, pageSize)
            if (newCountries.isEmpty()) {
                _isButtonVisible.value = false
            } else {
                _countries.value = _countries.value + newCountries
                currentPage++
            }
        }
    }

    // Function to handle item click
    fun onCountryClick(country: Country) {
        _selectedCountry = country
    }

    fun resetCountrySelected() {
        _selectedCountry = null
    }
}