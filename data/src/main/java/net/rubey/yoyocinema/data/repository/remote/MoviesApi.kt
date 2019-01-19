package net.rubey.yoyocinema.data.repository.remote

import kotlinx.coroutines.Deferred
import net.rubey.yoyocinema.data.entities.MovieDetailsData
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesApi {

    @GET("movie/{id}?append_to_response=videos,reviews")
    fun getMovie(@Path("id") movieId: Int): Deferred<MovieDetailsData>

    @GET("search/movie")
    fun searchMovies(@Query("query") query: String): Deferred<MoviesApiListResult>
}