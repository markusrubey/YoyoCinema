package net.rubey.yoyocinema.favorites

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_favorite_movie.view.*
import net.rubey.yoyocinema.entities.Movie

class FavoriteMoviesViewHolder(
    itemView: View,
    private val clickListener: View.OnClickListener
) : RecyclerView.ViewHolder(itemView) {

    fun bind(movie: Movie) = with(itemView) {
        moviePosterImageView.tag = movie.id
        moviePosterImageView.setOnClickListener(clickListener)

        Picasso.get().load(movie.posterPath).into(moviePosterImageView)
    }
}