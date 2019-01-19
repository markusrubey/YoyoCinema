package net.rubey.yoyocinema.domain.entities

data class MovieEntity(
    var id: Int = 0,
    var voteCount: Int = 0,
    var video: Boolean = false,
    var voteAverage: Double = 0.0,
    var popularity: Double = 0.0,
    var details: MovieDetailsEntity? = null,
    var title: String? = null,
    var posterPath: String? = null,
    var originalLanguage: String? = null,
    var originalTitle: String? = null,
    var backdropPath: String? = null,
    var releaseDate: String? = null,
    var overview: String? = null
)