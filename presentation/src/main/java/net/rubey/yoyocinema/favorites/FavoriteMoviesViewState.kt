package net.rubey.yoyocinema.favorites

import net.rubey.yoyocinema.entities.Movie

data class FavoriteMoviesViewState(
    val isLoading: Boolean = true,
    val isEmpty: Boolean = true,
    val movies: List<Movie>? = null
)