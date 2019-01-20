package net.rubey.yoyocinema.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import net.rubey.yoyocinema.domain.common.Mapper
import net.rubey.yoyocinema.domain.entities.MovieEntity
import net.rubey.yoyocinema.domain.usecases.SearchMovie
import net.rubey.yoyocinema.entities.Movie

class MovieSearchViewModelFactory(
    private val searchMovie: SearchMovie,
    private val mapper: Mapper<MovieEntity, Movie>
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MovieSearchViewModel(
            searchMovie,
            mapper
        ) as T
    }
}