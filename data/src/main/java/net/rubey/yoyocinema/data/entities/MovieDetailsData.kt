package net.rubey.yoyocinema.data.entities

import com.google.gson.annotations.SerializedName

class MovieDetailsData(

    @SerializedName("id")
    var id: Int = -1,

    @SerializedName("budget")
    var budget: Int? = null,

    @SerializedName("genres")
    var genres: List<MovieGenreData>? = null,

    @SerializedName("homepage")
    var homepage: String? = null,

    @SerializedName("popularity")
    var popularity: Double = 0.0,

    @SerializedName("revenue")
    var revenue: Int? = null,

    @SerializedName("runtime")
    var runtime: Int? = null,

    @SerializedName("tagline")
    var tagline: String? = null,

    @SerializedName("video")
    var video: Boolean = false,

    @SerializedName("vote_average")
    var voteAverage: Double = 0.0,

    @SerializedName("vote_count")
    var voteCount: Int = 0,

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

    @SerializedName("overview")
    var overview: String? = null,

    @SerializedName("release_date")
    var releaseDate: String? = null
)