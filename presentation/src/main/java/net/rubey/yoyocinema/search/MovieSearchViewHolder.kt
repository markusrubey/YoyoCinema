package net.rubey.yoyocinema.search

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_favorite_movie.view.*
import net.rubey.yoyocinema.entities.Movie

class MovieSearchViewHolder(
    itemView: View,
    private val clickListener: View.OnClickListener
) : RecyclerView.ViewHolder(itemView) {

    fun bind(movie: Movie) = with(itemView) {
        moviePosterContainer.tag = movie.id
        moviePosterContainer.setOnClickListener(clickListener)
        moviePosterContainer.clipToOutline = true

        Picasso.get().load(movie.posterPath).into(moviePosterImageView)
    }
}