package net.rubey.yoyocinema.data.repository.local

import androidx.room.Database
import androidx.room.RoomDatabase
import net.rubey.yoyocinema.data.entities.MovieData

@Database(entities = [MovieData::class], version = 1, exportSchema = false)
abstract class MoviesDatabase : RoomDatabase() {

    abstract fun getMoviesDao(): MoviesDao

    companion object {
        const val NAME = "movies_db"
    }
}