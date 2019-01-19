package net.rubey.yoyocinema.di.modules

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import net.rubey.yoyocinema.data.mappers.MovieDataEntityMapper
import net.rubey.yoyocinema.data.mappers.MovieEntityDataMapper
import net.rubey.yoyocinema.data.repository.MoviesRepositoryImpl
import net.rubey.yoyocinema.data.repository.local.MoviesDatabase
import net.rubey.yoyocinema.data.repository.local.MoviesLocalDataSource
import net.rubey.yoyocinema.data.repository.remote.MoviesApi
import net.rubey.yoyocinema.data.repository.remote.MoviesRemoteDataSource
import net.rubey.yoyocinema.di.DI
import net.rubey.yoyocinema.domain.repository.MoviesDataSource
import net.rubey.yoyocinema.domain.repository.MoviesRepository
import javax.inject.Named
import javax.inject.Singleton

@Module
class DataModule {

    @Singleton
    @Provides
    fun provideRoomDatabase(context: Context): MoviesDatabase {
        return Room.databaseBuilder(
            context,
            MoviesDatabase::class.java,
            MoviesDatabase.NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideMovieRepository(
        @Named(DI.MOVIES_LOCAL_DATA_SOURCE) localDataSource: MoviesDataSource,
        @Named(DI.MOVIES_REMOTE_DATA_SOURCE) remoteDataSource: MoviesDataSource
    ): MoviesRepository {
        return MoviesRepositoryImpl(localDataSource, remoteDataSource)
    }

    @Singleton
    @Provides
    @Named(DI.MOVIES_LOCAL_DATA_SOURCE)
    fun provideMoviesLocalDataSource(
        moviesDatabase: MoviesDatabase,
        entityDataMapper: MovieEntityDataMapper,
        dataEntityMapper: MovieDataEntityMapper
    ): MoviesDataSource {
        return MoviesLocalDataSource(moviesDatabase, entityDataMapper, dataEntityMapper)
    }

    @Singleton
    @Provides
    @Named(DI.MOVIES_REMOTE_DATA_SOURCE)
    fun provideMoviesRemoteDataSource(api: MoviesApi): MoviesDataSource {
        return MoviesRemoteDataSource(api)
    }
}