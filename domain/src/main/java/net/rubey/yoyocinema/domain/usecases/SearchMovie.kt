package net.rubey.yoyocinema.domain.usecases

import net.rubey.yoyocinema.domain.entities.MovieEntity
import net.rubey.yoyocinema.domain.repository.MoviesRepository

class SearchMovie(private val moviesRepository: MoviesRepository) {

    suspend fun execute(query: String): List<MovieEntity> {
        return moviesRepository.searchMovies(query)
    }
}