package net.rubey.yoyocinema.data.repository.remote

import net.rubey.yoyocinema.data.entities.Movie

data class MovieSerachResult(
    val page: Int,
    val total_results: Int,
    val total_pages: Int,
    val results: List<Movie>
)