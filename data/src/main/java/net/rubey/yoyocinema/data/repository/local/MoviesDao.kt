package net.rubey.yoyocinema.data.repository.local

import androidx.room.*
import net.rubey.yoyocinema.data.entities.MovieData

@Dao
interface MoviesDao {

    @Query("SELECT * FROM movies")
    fun getMovies(): List<MovieData>

    @Query("SELECT * FROM movies WHERE id=:movieId")
    fun getMovie(movieId: Int): MovieData?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveMovie(movie: MovieData)

    @Delete
    fun removeMovie(movie: MovieData)

    @Query("DELETE FROM movies")
    fun clear()
}