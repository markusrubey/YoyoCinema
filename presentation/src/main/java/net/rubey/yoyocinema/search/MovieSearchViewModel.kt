package net.rubey.yoyocinema.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import net.rubey.yoyocinema.domain.common.Mapper
import net.rubey.yoyocinema.domain.entities.MovieEntity
import net.rubey.yoyocinema.domain.usecases.SearchMovie
import net.rubey.yoyocinema.entities.Movie

class MovieSearchViewModel(
    private val searchMovie: SearchMovie,
    private val mapper: Mapper<MovieEntity, Movie>
) : ViewModel() {

    var viewState: MutableLiveData<MovieSearchViewState> = MutableLiveData()
    var errorState: MutableLiveData<Boolean> = MutableLiveData()

    fun searchMovie(query: String) {
        viewState.value = MovieSearchViewState(isLoading = true)

        GlobalScope.launch {
            try {
                val movies = searchMovie.execute(query).map {
                    mapper.mapFrom(it)
                }
                withContext(Dispatchers.Main) {
                    val newViewState = viewState.value?.copy(
                        isLoading = false,
                        movies = movies
                    )
                    viewState.value = newViewState
                    errorState.value = null
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    viewState.value = viewState.value?.copy(isLoading = false)
                    errorState.value = true
                }
            }
        }
    }
}