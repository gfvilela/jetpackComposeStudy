package br.dev.gustavovilela.firstprojectcompose.presentation.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.dev.gustavovilela.firstprojectcompose.domain.usecase.GetCountriesUseCase

class CountryListViewModelFactory(
    private val getCountriesUseCase: GetCountriesUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CountryListViewModel::class.java)) {
            return CountryListViewModel(getCountriesUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}