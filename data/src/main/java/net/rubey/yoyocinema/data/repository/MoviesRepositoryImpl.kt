package net.rubey.yoyocinema.data.repository

import net.rubey.yoyocinema.domain.entities.MovieEntity
import net.rubey.yoyocinema.domain.repository.MoviesDataSource
import net.rubey.yoyocinema.domain.repository.MoviesRepository

class MoviesRepositoryImpl(
    private val localDataSource: MoviesDataSource,
    private val remoteDataSource: MoviesDataSource
) : MoviesRepository {

    override suspend fun saveMovie(movieEntity: MovieEntity) {
        localDataSource.saveMovie(movieEntity)
    }

    override suspend fun removeMovie(movieEntity: MovieEntity) {
        localDataSource.removeMovie(movieEntity)
    }

    override suspend fun getMovie(movieId: Int): MovieEntity {
        val localMovieResult = localDataSource.getMovie(movieId)

        return if (localMovieResult.id != -1) {
            localMovieResult
        } else {
            remoteDataSource.getMovie(movieId)
        }
    }

    override suspend fun getMovies(): List<MovieEntity> {
        return localDataSource.getMovies()
    }

    override suspend fun searchMovies(query: String): List<MovieEntity> {
        return remoteDataSource.searchMovies(query)
    }
}