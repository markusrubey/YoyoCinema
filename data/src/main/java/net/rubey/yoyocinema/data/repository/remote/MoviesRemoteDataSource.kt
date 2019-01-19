package net.rubey.yoyocinema.data.repository.remote

import net.rubey.yoyocinema.data.mappers.MovieDataEntityMapper
import net.rubey.yoyocinema.data.mappers.MovieDetailsDataEntityMapper
import net.rubey.yoyocinema.domain.entities.MovieEntity
import net.rubey.yoyocinema.domain.repository.MoviesDataSource

class MoviesRemoteDataSource(private val api: MoviesApi) : MoviesDataSource {

    private val movieDataMapper = MovieDataEntityMapper()

    private val movieDetailsMapper = MovieDetailsDataEntityMapper()

    override suspend fun saveMovie(movieEntity: MovieEntity) {
        TODO("not implemented") // Out of scope.
    }

    override suspend fun getMovie(movieId: Int): MovieEntity {
        return movieDetailsMapper.mapFrom(api.getMovie(movieId).await())
    }

    override suspend fun getMovies(): List<MovieEntity> {
        TODO("not implemented") // Out of scope.
    }

    override suspend fun searchMovies(query: String): List<MovieEntity> {
        return api.searchMovies(query).await().results.map {
            movieDataMapper.mapFrom(it)
        }
    }
}