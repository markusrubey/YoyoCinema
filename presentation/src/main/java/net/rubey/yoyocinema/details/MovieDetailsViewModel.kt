package net.rubey.yoyocinema.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import net.rubey.yoyocinema.domain.common.Mapper
import net.rubey.yoyocinema.domain.entities.MovieEntity
import net.rubey.yoyocinema.domain.usecases.GetMovieDetails
import net.rubey.yoyocinema.domain.usecases.RemoveFavoriteMovie
import net.rubey.yoyocinema.domain.usecases.SaveFavoriteMovie
import net.rubey.yoyocinema.entities.Movie

class MovieDetailsViewModel(
    private val getMovieDetails: GetMovieDetails,
    private val saveFavoriteMovie: SaveFavoriteMovie,
    private val removeFavoriteMovie: RemoveFavoriteMovie,
    private val mapper: Mapper<MovieEntity, Movie>,
    private val movieId: Int
) : ViewModel() {

    var viewState: MutableLiveData<MovieDetailsViewState> = MutableLiveData()
    var favoriteState: MutableLiveData<Boolean> = MutableLiveData()
    var errorState : MutableLiveData<Boolean> = MutableLiveData()

    private var favoriteButtonClickJob : Job? = null

    lateinit var movieEntity: MovieEntity

    init {
        viewState.value = MovieDetailsViewState(isLoading = true)
    }

    fun getMovieDetails() {
        GlobalScope.launch {
            try {
                movieEntity = getMovieDetails.execute(movieId)
                withContext(Dispatchers.Main) {
                    updateErrorState(false)
                    updateMovieDetails(mapper.mapFrom(movieEntity))
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    updateErrorState(true)
                }
            }
        }
    }

    fun favoriteButtonClicked() {
        favoriteButtonClickJob?.cancel()
        favoriteButtonClickJob = GlobalScope.launch {
            val isFavorite = favoriteState.value == true

            if (isFavorite) {
                removeFavoriteMovie.execute(movieEntity)
            } else {
                saveFavoriteMovie.execute(movieEntity)
            }

            withContext(Dispatchers.Main) {
                updateFavoriteStatus(!isFavorite)
            }
        }
    }

    private fun updateMovieDetails(movie: Movie) {
        val newViewState = viewState.value?.copy(
            isLoading = false,
            title = movie.originalTitle,
            homepage = movie.details?.homepage,
            overview = movie.details?.overview,
            releaseDate = movie.releaseDate,
            votesAverage = movie.voteAverage,
            backdropUrl = movie.backdropPath,
            genres = movie.details?.genres
        )

        viewState.value = newViewState
    }

    private fun updateFavoriteStatus(isFavorite: Boolean) {
        favoriteState.value = isFavorite
    }

    private fun updateErrorState(error: Boolean) {
        errorState.value = error
    }
}