package net.rubey.yoyocinema.data.repository.remote

import net.rubey.yoyocinema.data.entities.MovieData

data class MoviesApiListResult(
    val page: Int,
    val total_results: Int,
    val total_pages: Int,
    val results: List<MovieData>
)