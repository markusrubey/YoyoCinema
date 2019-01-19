package net.rubey.yoyocinema.di.favorites

import dagger.Module
import dagger.Provides
import net.rubey.yoyocinema.domain.repository.MoviesRepository
import net.rubey.yoyocinema.domain.usecases.GetFavoriteMovies
import net.rubey.yoyocinema.favorites.FavoriteMoviesViewModelFactory
import net.rubey.yoyocinema.mappers.MovieEntityMovieMapper

@Module
class FavoriteMoviesModule {

    @Provides
    fun provideGetFavoriteMovies(moviesRepository: MoviesRepository): GetFavoriteMovies {
        return GetFavoriteMovies(moviesRepository)
    }

    @Provides
    fun provideFavoriteMoviesViewModelFactory(
        getFavoriteMovies: GetFavoriteMovies,
        mapper: MovieEntityMovieMapper
    ): FavoriteMoviesViewModelFactory {
        return FavoriteMoviesViewModelFactory(
            getFavoriteMovies,
            mapper
        )
    }
}