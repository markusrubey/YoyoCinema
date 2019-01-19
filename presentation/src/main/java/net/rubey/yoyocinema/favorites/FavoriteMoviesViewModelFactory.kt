package net.rubey.yoyocinema.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import net.rubey.yoyocinema.domain.common.Mapper
import net.rubey.yoyocinema.domain.entities.MovieEntity
import net.rubey.yoyocinema.domain.usecases.GetFavoriteMovies
import net.rubey.yoyocinema.entities.Movie

class FavoriteMoviesViewModelFactory(
    private val getFavoriteMovies: GetFavoriteMovies,
    private val mapper: Mapper<MovieEntity, Movie>
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return FavoriteMoviesViewModel(
            getFavoriteMovies,
            mapper
        ) as T
    }
}