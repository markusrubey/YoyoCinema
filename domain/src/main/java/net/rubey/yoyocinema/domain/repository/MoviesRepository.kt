package net.rubey.yoyocinema.domain.repository

import net.rubey.yoyocinema.domain.entities.MovieEntity
import java.io.IOException

interface MoviesRepository {

    suspend fun saveMovie(movieEntity : MovieEntity)

    suspend fun removeMovie(movieEntity: MovieEntity)

    @Throws(IOException::class)
    suspend fun getMovie(movieId : Int) : MovieEntity

    suspend fun getMovies() : List<MovieEntity>

    suspend fun searchMovies(query : String) : List<MovieEntity>
}