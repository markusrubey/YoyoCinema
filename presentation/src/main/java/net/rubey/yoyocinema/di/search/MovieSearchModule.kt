package net.rubey.yoyocinema.di.search

import dagger.Module
import dagger.Provides
import net.rubey.yoyocinema.domain.repository.MoviesRepository
import net.rubey.yoyocinema.domain.usecases.SearchMovie
import net.rubey.yoyocinema.mappers.MovieEntityMovieMapper
import net.rubey.yoyocinema.search.MovieSearchViewModelFactory

@Module
class MovieSearchModule {

    @Provides
    fun provideSearchMovie(moviesRepository: MoviesRepository): SearchMovie {
        return SearchMovie(moviesRepository)
    }

    @Provides
    fun provideSearchMovieViewModelFactory(
        searchMovie: SearchMovie,
        mapper: MovieEntityMovieMapper
    ): MovieSearchViewModelFactory {
        return MovieSearchViewModelFactory(
            searchMovie,
            mapper
        )
    }
}