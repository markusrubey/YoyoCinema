package net.rubey.yoyocinema.entities

data class Movie(
    var id: Int = 0,
    var voteCount: Int = 0,
    var video: Boolean = false,
    var voteAverage: Double = 0.0,
    var title: String? = null,
    var popularity: Double = 0.0,
    var posterPath: String? = null,
    var originalLanguage: String? = null,
    var originalTitle: String? = null,
    var backdropPath: String? = null,
    var adult: Boolean = false,
    var favorite: Boolean = false,
    var releaseDate: String? = null,
    var details: MovieDetails? = null,
    var overview: String? = null
)