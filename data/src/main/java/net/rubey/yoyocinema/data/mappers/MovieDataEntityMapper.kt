package net.rubey.yoyocinema.data.mappers

import net.rubey.yoyocinema.data.entities.MovieData
import net.rubey.yoyocinema.domain.common.Mapper
import net.rubey.yoyocinema.domain.entities.MovieEntity
import javax.inject.Inject

class MovieDataEntityMapper @Inject constructor() : Mapper<MovieData, MovieEntity>() {
    override fun mapFrom(from: MovieData): MovieEntity {
        return MovieEntity(
            id = from.id,
            voteCount = from.voteCount,
            voteAverage = from.voteAverage,
            popularity = from.popularity,
            title = from.title,
            posterPath = from.posterPath,
            originalLanguage = from.originalLanguage,
            backdropPath = from.backdropPath,
            originalTitle = from.originalTitle,
            releaseDate = from.releaseDate,
            overview = from.overview,
            favorite = from.favorite
        )
    }
}