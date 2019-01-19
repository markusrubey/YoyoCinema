package net.rubey.yoyocinema.mappers

import net.rubey.yoyocinema.domain.common.Mapper
import net.rubey.yoyocinema.domain.entities.MovieEntity
import net.rubey.yoyocinema.entities.Movie
import net.rubey.yoyocinema.entities.MovieDetails
import javax.inject.Inject

class MovieEntityMovieMapper @Inject constructor(): Mapper<MovieEntity, Movie>() {

    companion object {
        const val posterBaseUrl = "https://image.tmdb.org/t/p/w342"
        const val backdropBaseUrl = "https://image.tmdb.org/t/p/w780"
    }

    override fun mapFrom(from: MovieEntity): Movie {
        val movie = Movie(
            id = from.id,
            voteCount = from.voteCount,
            video = from.video,
            voteAverage = from.voteAverage,
            title = from.title,
            popularity = from.popularity,
            originalLanguage = from.originalLanguage,
            posterPath = from.posterPath?.let { posterBaseUrl + from.posterPath },
            backdropPath = from.backdropPath?. let { backdropBaseUrl + from.backdropPath },
            originalTitle = from.originalTitle,
            releaseDate = from.releaseDate,
            overview = from.overview,
            favorite = from.favorite
        )

        val fromDetails = from.details ?: return movie

        val details = MovieDetails()
        details.belongsToCollection = fromDetails.belongsToCollection
        details.budget = fromDetails.budget
        details.homepage = fromDetails.homepage
        details.overview = fromDetails.overview
        details.revenue = fromDetails.revenue
        details.runtime = fromDetails.runtime
        details.status = fromDetails.status
        details.tagline = fromDetails.tagline
        movie.details = details

        fromDetails.genres?.let {
            details.genres = it.map { genre -> genre.name }
        }

        return movie
    }
}