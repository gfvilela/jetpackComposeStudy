package br.dev.gustavovilela.firstprojectcompose.domain.usecase

import br.dev.gustavovilela.firstprojectcompose.domain.model.Country
import br.dev.gustavovilela.firstprojectcompose.domain.repository.ICountryRepository

class GetCountriesUseCase(private val repository: ICountryRepository) {
    suspend fun execute(page: Int, pageSize: Int): List<Country> {
        return repository.getCountries(page, pageSize)
    }
}