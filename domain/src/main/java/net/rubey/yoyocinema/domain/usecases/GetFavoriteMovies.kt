package net.rubey.yoyocinema.domain.usecases

import net.rubey.yoyocinema.domain.common.Result
import net.rubey.yoyocinema.domain.entities.MovieEntity
import net.rubey.yoyocinema.domain.repository.MoviesRepository

class GetFavoriteMovies(private val moviesRepository: MoviesRepository) {

    suspend fun execute(): Result<List<MovieEntity>> {
        return try {
            Result.Success(moviesRepository.getMovies())
        } catch (e: Exception) {
            Result.Error(e.message, e)
        }
    }
}