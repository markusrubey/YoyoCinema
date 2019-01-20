package net.rubey.yoyocinema.details

import net.rubey.yoyocinema.entities.Movie

data class MovieDetailsViewState(
    var isLoading: Boolean = true,
    var movie: Movie? = null
)