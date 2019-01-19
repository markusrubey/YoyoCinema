package net.rubey.yoyocinema.domain.usecases

import net.rubey.yoyocinema.domain.entities.MovieEntity
import net.rubey.yoyocinema.domain.repository.MoviesRepository

class GetMovieDetails(private val moviesRepository: MoviesRepository) {

    suspend fun execute(movieId: Int): MovieEntity {
        return moviesRepository.getMovie(movieId)
    }
}
