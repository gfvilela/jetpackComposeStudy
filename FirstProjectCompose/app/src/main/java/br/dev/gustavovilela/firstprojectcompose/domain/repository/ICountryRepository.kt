package br.dev.gustavovilela.firstprojectcompose.domain.repository

import br.dev.gustavovilela.firstprojectcompose.domain.model.Country

interface ICountryRepository {
    suspend fun getCountries(page: Int, pageSize: Int): List<Country>
}