package net.rubey.yoyocinema.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import net.rubey.yoyocinema.domain.common.Mapper
import net.rubey.yoyocinema.domain.entities.MovieEntity
import net.rubey.yoyocinema.domain.usecases.GetMovieDetails
import net.rubey.yoyocinema.domain.usecases.RemoveFavoriteMovie
import net.rubey.yoyocinema.domain.usecases.SaveFavoriteMovie
import net.rubey.yoyocinema.entities.Movie

class MovieDetailsViewModelFactory(
    private val getMovieDetails: GetMovieDetails,
    private val saveFavoriteMovie: SaveFavoriteMovie,
    private val removeFavoriteMovie: RemoveFavoriteMovie,
    private val mapper: Mapper<MovieEntity, Movie>
) : ViewModelProvider.Factory {

    var movieId: Int = -1

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MovieDetailsViewModel(
            getMovieDetails,
            saveFavoriteMovie,
            removeFavoriteMovie,
            mapper,
            movieId
        ) as T
    }
}