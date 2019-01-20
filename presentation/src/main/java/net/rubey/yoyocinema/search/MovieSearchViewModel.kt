package net.rubey.yoyocinema.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import net.rubey.yoyocinema.domain.common.Mapper
import net.rubey.yoyocinema.domain.common.Result
import net.rubey.yoyocinema.domain.entities.MovieEntity
import net.rubey.yoyocinema.domain.usecases.SearchMovie
import net.rubey.yoyocinema.entities.Movie

class MovieSearchViewModel(
    private val searchMovie: SearchMovie,
    private val mapper: Mapper<MovieEntity, Movie>
) : ViewModel() {

    var viewState: MutableLiveData<MovieSearchViewState> = MutableLiveData()

    private var searchMovieJob: Job? = null

    override fun onCleared() {
        super.onCleared()

        searchMovieJob?.cancel()
    }

    fun searchMovie(query: String) {
        viewState.value = MovieSearchViewState(isLoading = true)

        searchMovieJob?.cancel()
        searchMovieJob = GlobalScope.launch {
            val result = searchMovie.execute(query)
            when (result) {
                is Result.Success -> processMovieSearchResult(result.value)
                is Result.Error -> processMovieSearchError()
            }
        }
    }

    private suspend fun processMovieSearchResult(result: List<MovieEntity>) {
        withContext(Dispatchers.Main) {
            viewState.value = viewState.value?.copy(
                isLoading = false,
                movies = result.map(mapper::mapFrom)
            )
        }
    }

    private suspend fun processMovieSearchError() {
        withContext(Dispatchers.Main) {
            viewState.value = viewState.value?.copy(isLoading = false)
        }
    }
}