package net.rubey.yoyocinema.domain.usecases

import net.rubey.yoyocinema.domain.common.Result
import net.rubey.yoyocinema.domain.entities.MovieEntity
import net.rubey.yoyocinema.domain.repository.MoviesRepository

class GetMovieDetails(private val moviesRepository: MoviesRepository) {

    suspend fun execute(movieId: Int): Result<MovieEntity> {
        return try {
            Result.Success(moviesRepository.getMovie(movieId))
        } catch (e: Exception) {
            Result.Error(e.message, e)
        }
    }
}
