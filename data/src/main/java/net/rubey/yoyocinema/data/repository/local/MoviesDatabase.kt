package net.rubey.yoyocinema.data.repository.local

import androidx.room.Database
import androidx.room.RoomDatabase
import net.rubey.yoyocinema.data.entities.MovieData

@Database(entities = [MovieData::class], version = 1)
abstract class MoviesDatabase : RoomDatabase() {

    abstract fun getMoviesDao(): MoviesDao
}