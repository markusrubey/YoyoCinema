package net.rubey.yoyocinema.search

import net.rubey.yoyocinema.entities.Movie

data class MovieSearchViewState(
    val isLoading: Boolean = true,
    val movies: List<Movie>? = null
)