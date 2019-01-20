package net.rubey.yoyocinema.domain.usecases

import net.rubey.yoyocinema.domain.common.Result
import net.rubey.yoyocinema.domain.entities.MovieEntity
import net.rubey.yoyocinema.domain.repository.MoviesRepository

class RemoveFavoriteMovie(private val moviesRepository: MoviesRepository) {

    suspend fun execute(movieEntity: MovieEntity): Result<Boolean> {
        return try {
            moviesRepository.removeMovie(movieEntity)
            Result.Success(false)
        } catch (e: Exception) {
            Result.Error(e.message, e)
        }
    }
}
