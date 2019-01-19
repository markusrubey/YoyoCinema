package net.rubey.yoyocinema.domain.usecases

import net.rubey.yoyocinema.domain.entities.MovieEntity
import net.rubey.yoyocinema.domain.repository.MoviesRepository

class SaveFavoriteMovie(private val moviesRepository: MoviesRepository) {

    suspend fun execute(movieEntity: MovieEntity) {
        movieEntity.favorite = true
        moviesRepository.saveMovie(movieEntity)
    }
}
