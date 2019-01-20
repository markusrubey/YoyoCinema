package net.rubey.yoyocinema.domain.usecases

import net.rubey.yoyocinema.domain.common.Result
import net.rubey.yoyocinema.domain.entities.MovieEntity
import net.rubey.yoyocinema.domain.repository.MoviesRepository

class SearchMovie(private val moviesRepository: MoviesRepository) {

    suspend fun execute(query: String): Result<List<MovieEntity>> {
        return try {
            Result.Success(moviesRepository.searchMovies(query))
        } catch (e: Exception) {
            Result.Error(e.message, e)
        }
    }
}