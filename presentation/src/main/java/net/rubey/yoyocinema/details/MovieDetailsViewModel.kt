package net.rubey.yoyocinema.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import net.rubey.yoyocinema.domain.common.Mapper
import net.rubey.yoyocinema.domain.common.Result
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

    private var getFavoriteMovieJob: Job? = null
    private var favoriteMovieJob: Job? = null

    lateinit var movieEntity: MovieEntity

    override fun onCleared() {
        super.onCleared()

        getFavoriteMovieJob?.cancel()
        favoriteMovieJob?.cancel()
    }

    fun getMovieDetails() {
        viewState.value = MovieDetailsViewState(isLoading = true)

        getFavoriteMovieJob?.cancel()
        getFavoriteMovieJob = GlobalScope.launch {
            val result = getMovieDetails.execute(movieId)
            when (result) {
                is Result.Success -> processMovieDetails(result.value)
                is Result.Error -> processMovieDetailsError()
            }
        }
    }

    private suspend fun processMovieDetails(result: MovieEntity) {
        withContext(Dispatchers.Main) {
            movieEntity = result
            val movie = mapper.mapFrom(movieEntity)
            viewState.value = viewState.value?.copy(
                isLoading = false,
                movie = movie
            )
            favoriteState.value = movie.favorite
        }
    }

    private suspend fun processMovieDetailsError() {
        withContext(Dispatchers.Main) {
            viewState.value = viewState.value?.copy(
                isLoading = false,
                movie = null
            )
        }
    }

    fun favoriteButtonClicked() {
        favoriteMovieJob?.cancel()
        favoriteMovieJob = GlobalScope.launch {
            val result = if (favoriteState.value == true) {
                removeFavoriteMovie.execute(movieEntity)
            } else {
                saveFavoriteMovie.execute(movieEntity)
            }

            when (result) {
                is Result.Success -> processMovieFavorite(result.value)
                is Result.Error -> processMovieFavoriteError()
            }
        }
    }

    private suspend fun processMovieFavorite(isFavorite: Boolean) {
        withContext(Dispatchers.Main) {
            favoriteState.value = isFavorite
        }
    }

    private suspend fun processMovieFavoriteError() {
        // TODO
    }
}