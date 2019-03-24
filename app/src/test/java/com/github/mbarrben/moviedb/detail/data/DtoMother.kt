package com.github.mbarrben.moviedb.detail.data

import com.github.mbarrben.moviedb.CommonConstants.Companion.ANY_BUDGET
import com.github.mbarrben.moviedb.CommonConstants.Companion.ANY_COMPANY_NAME
import com.github.mbarrben.moviedb.CommonConstants.Companion.ANY_COUNTRY_ISO
import com.github.mbarrben.moviedb.CommonConstants.Companion.ANY_COUNTRY_NAME
import com.github.mbarrben.moviedb.CommonConstants.Companion.ANY_GENRE_NAME
import com.github.mbarrben.moviedb.CommonConstants.Companion.ANY_ID
import com.github.mbarrben.moviedb.CommonConstants.Companion.ANY_LANGUAGE_ISO
import com.github.mbarrben.moviedb.CommonConstants.Companion.ANY_LANGUAGE_NAME
import com.github.mbarrben.moviedb.CommonConstants.Companion.ANY_REVENUE
import com.github.mbarrben.moviedb.CommonConstants.Companion.ANY_RUNTIME
import com.github.mbarrben.moviedb.CommonConstants.Companion.ANY_STATUS
import com.github.mbarrben.moviedb.CommonConstants.Companion.ANY_TAGLINE
import com.github.mbarrben.moviedb.CommonConstants.Companion.ANY_URL
import com.github.mbarrben.moviedb.detail.data.network.model.Dto

class DtoMother {
    companion object {

        private fun aGenre() = Dto.Genre(
            id = ANY_ID,
            name = ANY_GENRE_NAME
        )

        private fun aCompany() = Dto.Company(
            id = ANY_ID,
            name = ANY_COMPANY_NAME
        )

        private fun aCountry() = Dto.Country(
            iso = ANY_COUNTRY_ISO,
            name = ANY_COUNTRY_NAME
        )

        private fun aLanguage() = Dto.Language(
            iso = ANY_LANGUAGE_ISO,
            name = ANY_LANGUAGE_NAME
        )

        fun aDetail() = Dto.Detail(
            budget = ANY_BUDGET,
            genres = listOf(
                aGenre(),
                aGenre()
            ),
            homepage = ANY_URL,
            productionCompanies = listOf(
                aCompany(),
                aCompany(),
                aCompany()
            ),
            productionCountries = listOf(
                aCountry(),
                aCountry()
            ),
            spokenLanguages = listOf(
                aLanguage(),
                aLanguage(),
                aLanguage()
            ),
            revenue = ANY_REVENUE,
            runtime = ANY_RUNTIME,
            status = ANY_STATUS,
            tagline = ANY_TAGLINE
        )
    }
}