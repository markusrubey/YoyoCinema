package net.rubey.yoyocinema.favorites

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import net.rubey.yoyocinema.domain.common.Mapper
import net.rubey.yoyocinema.domain.common.Result
import net.rubey.yoyocinema.domain.entities.MovieEntity
import net.rubey.yoyocinema.domain.usecases.GetFavoriteMovies
import net.rubey.yoyocinema.entities.Movie

class FavoriteMoviesViewModel(
    private val getFavoriteMovies: GetFavoriteMovies,
    private val mapper: Mapper<MovieEntity, Movie>
) : ViewModel() {

    var viewState: MutableLiveData<FavoriteMoviesViewState> = MutableLiveData()
    var errorState: MutableLiveData<Boolean> = MutableLiveData()

    private var getFavoriteMoviesJob: Job? = null

    override fun onCleared() {
        super.onCleared()

        getFavoriteMoviesJob?.cancel()
    }

    fun getFavoriteMovies() {
        viewState.value = FavoriteMoviesViewState(isLoading = true)

        getFavoriteMoviesJob?.cancel()
        getFavoriteMoviesJob = GlobalScope.launch {
            val result = getFavoriteMovies.execute()
            when (result) {
                is Result.Success -> processFavoriteMovies(result.value)
                is Result.Error -> processError()
            }
        }
    }

    private suspend fun processFavoriteMovies(result: List<MovieEntity>) {
        withContext(Dispatchers.Main) {
            val movies = result.map(mapper::mapFrom)
            val newViewState = viewState.value?.copy(
                isLoading = false,
                movies = movies
            )
            viewState.value = newViewState
            errorState.value = null
        }
    }

    private suspend fun processError() {
        withContext(Dispatchers.Main) {
            viewState.value = viewState.value?.copy(isLoading = false)
            errorState.value = true
        }
    }
}