//package com.example.flagquiz.assets
//
//import com.example.flagquiz.data.Country
//import com.example.flagquiz.data.CountryRepository
//
//class TestCountryRepository : CountryRepository {
//
//    private val testCountries = listOf(
//        Country("Brazil", "br", "pt"),
//        Country("United States", "us", "en"),
//        Country("France", "fr", "en"),
//        Country("Germany", "de", "en"),
//        Country("Japan", "jp", "en"),
//        Country("Italy", "it", "en"),
//        Country("Spain", "es", "en"),
//        Country("China", "cn", "en"),
//        Country("India", "in", "en"),
//        Country("Canada", "ca", "en"),
//        Country("Australia", "au", "en"),
//        Country("Mexico", "mx", "en"),
//        Country("Argentina", "ar", "en"),
//        Country("United Kingdom", "gb", "en")
//    )
//
//    suspend fun getRandomCountries(count: Int, language: String): List<Country> {
//        return testCountries.shuffled().take(count)
//    }
//
//    fun getCountries(language: String): List<Country> {
//        return testCountries
//    }
//}