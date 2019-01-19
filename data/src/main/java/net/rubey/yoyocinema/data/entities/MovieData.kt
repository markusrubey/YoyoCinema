package net.rubey.yoyocinema.data.entities

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity(tableName = "movies")
data class MovieData(

    @SerializedName("id")
    val id: Int = -1,

    @SerializedName("vote_count")
    val voteCount: Int = 0,

    @SerializedName("video")
    val video: Boolean = false,

    @SerializedName("vote_average")
    val voteAverage: Double = 0.0,

    @SerializedName("popularity")
    val popularity: Double = 0.0,

    @SerializedName("title")
    var title: String? = null,

    @SerializedName("poster_path")
    var posterPath: String? = null,

    @SerializedName("original_language")
    var originalLanguage: String? = null,

    @SerializedName("original_title")
    var originalTitle: String? = null,

    @SerializedName("backdrop_path")
    var backdropPath: String? = null,

    @SerializedName("release_date")
    var releaseDate: String? = null,

    @SerializedName("overview")
    var overview: String? = null
)