package net.rubey.yoyocinema.search

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.fragment_movie_search.*
import net.rubey.yoyocinema.App
import net.rubey.yoyocinema.R
import net.rubey.yoyocinema.details.MovieDetailsFragment
import javax.inject.Inject

class MovieSearchFragment : Fragment(), View.OnClickListener {

    @Inject
    lateinit var viewModelFactory: MovieSearchViewModelFactory

    private lateinit var viewModel: MovieSearchViewModel

    private val movieSearchAdapter = MovieSearchAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        App.createMovieSearchComponent()?.inject(this)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MovieSearchViewModel::class.java)
        viewModel.viewState.observe(this, Observer(this::showViewState))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchMovieRecyclerView.layoutManager = GridLayoutManager(view.context, 3)
        searchMovieRecyclerView.adapter = movieSearchAdapter

        searchMovieEditText.setOnEditorActionListener(TextView.OnEditorActionListener { textView, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                viewModel.searchMovie(textView.text.toString())
                searchMovieEditText.clearFocus()
                showKeyboard(false)
                return@OnEditorActionListener true
            }
            false
        })

        searchMovieEditText.requestFocus()
        showKeyboard(true)
    }

    override fun onPause() {
        super.onPause()

        showKeyboard(false)
    }

    override fun onDestroy() {
        super.onDestroy()

        App.releaseMovieSearchComponent()
    }

    override fun onClick(view: View) {
        val movieId = view.tag as Int
        val bundle = bundleOf(MovieDetailsFragment.MOVIE_ID to movieId)
        Navigation.findNavController(view).navigate(R.id.action_searchFragment_to_detailsFragment, bundle)
    }

    private fun showViewState(state: MovieSearchViewState) {
        progressBar.visibility = if (state.isLoading) View.VISIBLE else View.INVISIBLE

        movieSearchAdapter.submitList(state.movies)
    }

    private fun showKeyboard(show: Boolean) {
        val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        if (show) {
            imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
        } else {
            imm.hideSoftInputFromWindow(searchMovieEditText.windowToken, 0)
        }
    }
}
