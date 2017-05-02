package com.github.mbarrben.moviedb.model.mappers

import com.github.mbarrben.moviedb.model.entities.Company
import com.github.mbarrben.moviedb.model.entities.Country
import com.github.mbarrben.moviedb.model.entities.Genre
import com.github.mbarrben.moviedb.model.entities.Language
import com.github.mbarrben.moviedb.model.entities.Movie.Details
import com.github.mbarrben.moviedb.model.entities.RestMovie.RestDetails

class DetailsMapper {

  fun map(restDetails: RestDetails): Details = with(restDetails) {
    Details(
        budget,
        genres.map { with(it) { Genre(id, name) } },
        homepage,
        productionCompanies.map { with(it) { Company(id, name) } },
        productionCountries.map { with(it) { Country(iso, name) } },
        spokenLanguages.map { with(it) { Language(iso, name) } },
        revenue,
        runtime,
        status,
        tagline
    )
  }

}