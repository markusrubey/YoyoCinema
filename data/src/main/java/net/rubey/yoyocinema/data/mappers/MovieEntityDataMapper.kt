package net.rubey.yoyocinema.data.mappers

import net.rubey.yoyocinema.data.entities.MovieData
import net.rubey.yoyocinema.domain.common.Mapper
import net.rubey.yoyocinema.domain.entities.MovieEntity
import javax.inject.Inject

class MovieEntityDataMapper @Inject constructor(): Mapper<MovieEntity, MovieData>() {
    override fun mapFrom(from: MovieEntity): MovieData {
        return MovieData(
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