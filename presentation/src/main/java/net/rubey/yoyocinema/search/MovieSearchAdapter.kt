package net.rubey.yoyocinema.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import net.rubey.yoyocinema.R
import net.rubey.yoyocinema.entities.Movie

class MovieSearchAdapter(private val clickListener: View.OnClickListener) :
    RecyclerView.Adapter<MovieSearchViewHolder>() {

    private val items = ArrayList<Movie>()

    fun submitList(movies: List<Movie>?) {
        items.clear()
        movies?.let(items::addAll)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieSearchViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.item_search_movie, parent, false)
        return MovieSearchViewHolder(itemView, clickListener)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: MovieSearchViewHolder, position: Int) {
        holder.bind(items[position])
    }
}