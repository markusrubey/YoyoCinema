package net.rubey.yoyocinema.domain.usecases

import net.rubey.yoyocinema.domain.entities.MovieEntity
import net.rubey.yoyocinema.domain.repository.MoviesRepository

class RemoveFavoriteMovie(private val moviesRepository: MoviesRepository) {

    suspend fun execute(movieEntity: MovieEntity) {
        moviesRepository.removeMovie(movieEntity)
    }
}
