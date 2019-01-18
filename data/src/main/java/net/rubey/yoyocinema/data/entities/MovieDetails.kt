package net.rubey.yoyocinema.data.entities

class MovieDetails(
    val adult: Boolean,
    val backdrop_path: String,
    val belongs_to_collection: String,
    val budget: Int,
    val genres: List<MovieGenre>,
    val homepage: String,
    val id: Int,
    val imbd_id: String,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val production_ompanies: List<MovieProductionCompany>,
    val production_countries: List<MovieProductionCountry>,
    val release_date: String,
    val revenue: Int,
    val runtime: Int,
    val spoken_languages: List<MovieLanguage>,
    val status: String,
    val tagline: String,
    val title: String,
    val video: Boolean,
    val vote_average: Float,
    val vote_count: Int
)