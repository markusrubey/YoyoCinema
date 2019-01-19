package net.rubey.yoyocinema.di.details

import dagger.Module
import dagger.Provides
import net.rubey.yoyocinema.details.MovieDetailsViewModelFactory
import net.rubey.yoyocinema.domain.repository.MoviesRepository
import net.rubey.yoyocinema.domain.usecases.GetMovieDetails
import net.rubey.yoyocinema.domain.usecases.RemoveFavoriteMovie
import net.rubey.yoyocinema.domain.usecases.SaveFavoriteMovie
import net.rubey.yoyocinema.mappers.MovieEntityMovieMapper

@Module
class MovieDetailsModule {

    @Provides
    fun provideRemoveFavoriteMovie(moviesRepository: MoviesRepository): RemoveFavoriteMovie {
        return RemoveFavoriteMovie(moviesRepository)
    }

    @Provides
    fun provideSaveFavoriteMovieUseCase(moviesRepository: MoviesRepository): SaveFavoriteMovie {
        return SaveFavoriteMovie(moviesRepository)
    }

    @Provides
    fun provideGetMovieDetailsUseCase(moviesRepository: MoviesRepository): GetMovieDetails {
        return GetMovieDetails(moviesRepository)
    }

    @Provides
    fun provideMovieDetailsViewModelFactory(
        getMovieDetails: GetMovieDetails,
        saveFavoriteMovie: SaveFavoriteMovie,
        removeFavoriteMovie: RemoveFavoriteMovie,
        mapper: MovieEntityMovieMapper
    ): MovieDetailsViewModelFactory {
        return MovieDetailsViewModelFactory(
            getMovieDetails,
            saveFavoriteMovie,
            removeFavoriteMovie,
            mapper
        )
    }
}