package net.rubey.yoyocinema.data.repository.local

import net.rubey.yoyocinema.data.entities.MovieData
import net.rubey.yoyocinema.domain.common.Mapper
import net.rubey.yoyocinema.domain.entities.MovieEntity
import net.rubey.yoyocinema.domain.repository.MoviesDataSource

class MoviesLocalDataSource(
    database: MoviesDatabase,
    private val entityToDataMapper: Mapper<MovieEntity, MovieData>,
    private val dataToEntityMapper: Mapper<MovieData, MovieEntity>
) : MoviesDataSource {

    private val dao = database.getMoviesDao()

    override suspend fun saveMovie(movieEntity: MovieEntity) {
        dao.saveMovie(entityToDataMapper.mapFrom(movieEntity))
    }

    override suspend fun getMovie(movieId: Int): MovieEntity {
        return dataToEntityMapper.mapFrom(dao.getMovie(movieId) ?: MovieData())
    }

    override suspend fun getMovies(): List<MovieEntity> {
        return dao.getMovies().map {
            dataToEntityMapper.mapFrom(it)
        }
    }

    override suspend fun searchMovies(query: String): List<MovieEntity> {
        TODO("not implemented") // Out of scope.
    }
}