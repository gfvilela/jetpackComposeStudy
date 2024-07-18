package br.dev.gustavovilela.firstprojectcompose.data.mock

import br.dev.gustavovilela.firstprojectcompose.domain.model.Country
import br.dev.gustavovilela.firstprojectcompose.domain.repository.ICountryRepository

class MockCountryRepository : ICountryRepository {
    override suspend fun getCountries(page: Int, pageSize: Int): List<Country> {
        // Simulating a static list of countries for mocking purposes
        val countries = listOf(
            Country("Brazil", "Brasilia"),
            Country("Canada", "Ottawa"),
            Country("Germany", "Berlin"),
            Country("Japan", "Tokyo"),
            Country("Australia", "Canberra"),
            Country("India", "New Delhi"),
            Country("France", "Paris"),
            Country("Italy", "Rome"),
            Country("Mexico", "Mexico City"),
            Country("Russia", "Moscow")
        )
        val start = (page - 1) * pageSize
        val end = start + pageSize
        if ( start >= countries.size) {
            return  listOf()
        }
        return countries.subList(
            start.coerceAtLeast(0),
            end.coerceAtMost(countries.size)
        )
    }
}