package net.rubey.yoyocinema.domain.repository

import net.rubey.yoyocinema.domain.entities.MovieEntity

interface MoviesDataSource {

    suspend fun saveMovie(movieEntity : MovieEntity)

    suspend fun getMovie(movieId : Int) : MovieEntity

    suspend fun getMovies() : List<MovieEntity>

    suspend fun searchMovies(query : String) : List<MovieEntity>
}