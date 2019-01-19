package net.rubey.yoyocinema.favorites

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import net.rubey.yoyocinema.R
import net.rubey.yoyocinema.entities.Movie

class FavoriteMoviesAdapter(private val clickListener: View.OnClickListener) :
    RecyclerView.Adapter<FavoriteMoviesViewHolder>() {

    private val items = ArrayList<Movie>()

    fun submitList(movies: List<Movie>) {
        items.clear()
        items.addAll(movies)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteMoviesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.item_favorite_movie, parent, false)
        return FavoriteMoviesViewHolder(itemView, clickListener)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: FavoriteMoviesViewHolder, position: Int) {
        holder.bind(items[position])
    }
}