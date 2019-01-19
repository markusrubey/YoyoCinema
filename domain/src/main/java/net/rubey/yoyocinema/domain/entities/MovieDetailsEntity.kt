package net.rubey.yoyocinema.domain.entities

data class MovieDetailsEntity(
    var belongsToCollection: Any? = null,
    var budget: Int? = null,
    var homepage: String? = null,
    var overview: String? = null,
    var revenue: Int? = null,
    var runtime: Int? = null,
    var status: String? = null,
    var tagline: String? = null,
    var genres: List<MovieGenreEntity>? = null
)