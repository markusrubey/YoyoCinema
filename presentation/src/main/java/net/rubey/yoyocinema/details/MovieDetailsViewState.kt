package net.rubey.yoyocinema.details

data class MovieDetailsViewState(
    var isLoading: Boolean = true,
    var title: String? = null,
    var overview: String? = null,
    var homepage: String? = null,
    var releaseDate: String? = null,
    var votesAverage: Double? = null,
    var posterPath: String? = null,
    var genres: List<String>? = null
)