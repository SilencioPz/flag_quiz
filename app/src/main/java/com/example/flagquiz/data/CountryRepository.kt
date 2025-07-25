package com.example.flagquiz.data

import com.example.flagquiz.R

object CountryRepository {
    //Em Inglês
    val countries = listOf(
        Country(
            nameEn = "Alaska",
            namePt = "Alasca",
            nameEs = "Alaska",
            flagResId = R.drawable.alaska,
            region = "America"
        ),
        Country(
            nameEn = "Argentina",
            namePt = "Argentina",
            nameEs = "Argentina",
            flagResId = R.drawable.argentina,
            region = "South America"
        ),
        Country(
            nameEn = "Brazil",
            namePt = "Brasil",
            nameEs = "Brasil",
            flagResId = R.drawable.brasil,
            region = "South America"
        ),
        Country(
            nameEn = "China",
            namePt = "China",
            nameEs = "China",
            flagResId = R.drawable.china,
            region = "Asia"
        ),
        Country(
            nameEn = "Costa Rica",
            namePt = "Costa Rica",
            nameEs = "Costa Rica",
            flagResId = R.drawable.costarica,
            region = "South America"
        ),
        Country(
            nameEn = "El Salvador",
            namePt = "El Salvador",
            nameEs = "El Salvador",
            flagResId = R.drawable.elsalvador,
            region = "South America"
        ),
        Country(
            nameEn = "Finland",
            namePt = "Finlândia",
            nameEs = "Finlandia",
            flagResId = R.drawable.finland,
            region = "Europe"
        ),
        Country(
            nameEn = "France",
            namePt = "França",
            nameEs = "Francia",
            flagResId = R.drawable.france,
            region = "Europe"
        ),
        Country(
            nameEn = "Germany",
            namePt = "Alemanha",
            nameEs = "Alemania",
            flagResId = R.drawable.germany,
            region = "Europe"
        ),
        Country(
            nameEn = "Guatemala",
            namePt = "Guatemala",
            nameEs = "Guatemala",
            flagResId = R.drawable.guatemala,
            region = "Central America"
        ),
        Country(
            nameEn = "Honduras",
            namePt = "Honduras",
            nameEs = "Honduras",
            flagResId = R.drawable.honduras,
            region = "Central America"
        ),
        Country(
            nameEn = "India",
            namePt = "Índia",
            nameEs = "India",
            flagResId = R.drawable.india,
            region = "Asia"
        ),
        Country(
            nameEn = "Italy",
            namePt = "Itália",
            nameEs = "Italia",
            flagResId = R.drawable.italy,
            region = "Europe"
        ),
        Country(
            nameEn = "Japan",
            namePt = "Japão",
            nameEs = "Japón",
            flagResId = R.drawable.japan,
            region = "Asia"
        ),
        Country(
            nameEn = "Mexico",
            namePt = "México",
            nameEs = "México",
            flagResId = R.drawable.mexico,
            region = "America"
        ),
        Country(
            nameEn = "Nicaragua",
            namePt = "Nicaragua",
            nameEs = "Nicaragua",
            flagResId = R.drawable.nicaragua,
            region = "Central America"
        ),
        Country(
            nameEn = "Norway",
            namePt = "Noruega",
            nameEs = "Noruega",
            flagResId = R.drawable.norway,
            region = "Europe"
        ),
        Country(
            nameEn = "Panama",
            namePt = "Panamá",
            nameEs = "Panamá",
            flagResId = R.drawable.panama,
            region = "Central America"
        ),
        Country(
            nameEn = "Russia",
            namePt = "Rússia",
            nameEs = "Rusia",
            flagResId = R.drawable.russia,
            region = "Europe"
        ),
        Country(
            nameEn = "South Africa",
            namePt = "África do Sul",
            nameEs = "África del Sur",
            flagResId = R.drawable.southafrica,
            region = "Africa"
        ),
        Country(
            nameEn = "South Korea",
            namePt = "Coréia do Sul",
            nameEs = "Corea del Sur",
            flagResId = R.drawable.southkorea,
            region = "Asia"
        ),
        Country(
            nameEn = "Spain",
            namePt = "Espanha",
            nameEs = "España",
            flagResId = R.drawable.spain,
            region = "Europe"
        ),
        Country(
            nameEn = "Switzerland",
            namePt = "Suíça",
            nameEs = "Suiza",
            flagResId = R.drawable.switzerland,
            region = "Europe"
        ),
        Country(
            nameEn = "Taiwan",
            namePt = "Taiwan",
            nameEs = "Taiwán",
            flagResId = R.drawable.taiwan,
            region = "Asia"
        ),
        Country(
            nameEn = "Thailand",
            namePt = "Tailândia",
            nameEs = "Tailandia",
            flagResId = R.drawable.thailand,
            region = "Asia"
        ),
        Country(
            nameEn = "United Arab Emirates",
            namePt = "Emirados Árabes Unidos",
            nameEs = "Emiratos Árabes Unidos",
            flagResId = R.drawable.unitedarabemirates,
            region = "Asia"
        ),
        Country(
            nameEn = "United Kingdom",
            namePt = "Reino Unido",
            nameEs = "Reino Unido",
            flagResId = R.drawable.unitedkingdom,
            region = "Europe"
        ),
        Country(
            nameEn = "United States of America",
            namePt = "Estados Unidos da América",
            nameEs = "Estados Unidos de América",
            flagResId = R.drawable.usa,
            region = "America"
        ),
        Country(
            nameEn = "Uruguay",
            namePt = "Uruguai",
            nameEs = "Uruguay",
            flagResId = R.drawable.uruguay,
            region = "South America"
        ),
        Country(
            nameEn = "Venezuela",
            namePt = "Venezuela",
            nameEs = "Venezuela",
            flagResId = R.drawable.venezuela,
            region = "South America"
        ),
    )

    open fun getRandomCountries(amount: Int, language: String): List<Country> {
        return getCountries(language).shuffled().take(amount)
    }

    open fun getCountries(language: String): List<Country> {
        return countries.map { country ->
            country.copy(name = when (language.lowercase()) {
                "pt" -> country.namePt
                "es" -> country.nameEs
                else -> country.nameEn
            })
        }
    }
}