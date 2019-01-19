package net.rubey.yoyocinema.data.mappers

import net.rubey.yoyocinema.data.entities.MovieDetailsData
import net.rubey.yoyocinema.domain.common.Mapper
import net.rubey.yoyocinema.domain.entities.MovieDetailsEntity
import net.rubey.yoyocinema.domain.entities.MovieEntity
import net.rubey.yoyocinema.domain.entities.MovieGenreEntity

class MovieDetailsDataEntityMapper : Mapper<MovieDetailsData, MovieEntity>() {

    override fun mapFrom(from: MovieDetailsData): MovieEntity {
        val movieEntity = MovieEntity(
            id = from.id,
            voteCount = from.voteCount,
            video = from.video,
            voteAverage = from.voteAverage,
            popularity = from.popularity,
            title = from.title,
            posterPath = from.posterPath,
            originalTitle = from.originalTitle,
            backdropPath = from.backdropPath,
            originalLanguage = from.originalLanguage,
            releaseDate = from.releaseDate,
            overview = from.overview
        )
        val details = MovieDetailsEntity()
        details.overview = from.overview
        details.budget = from.budget
        details.homepage = from.homepage
        details.revenue = from.revenue
        details.runtime = from.runtime
        details.tagline = from.tagline

        from.genres?.let {
            val genreEntities = it.map { genreData ->
                return@map MovieGenreEntity(genreData.id, genreData.name)
            }
            details.genres = genreEntities
        }

        movieEntity.details = details
        return movieEntity
    }
}