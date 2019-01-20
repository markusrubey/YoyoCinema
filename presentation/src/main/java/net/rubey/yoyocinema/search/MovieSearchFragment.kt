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
    lateinit var factory: MovieSearchViewModelFactory

    private lateinit var searchViewModel: MovieSearchViewModel

    private val movieSearchAdapter = MovieSearchAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (view.context.applicationContext as App).createMovieSearchComponent()?.inject(this)

        searchViewModel = ViewModelProviders.of(this, factory).get(MovieSearchViewModel::class.java)
        searchViewModel.viewState.observe(this, Observer(this::showViewState))

        searchMovieRecyclerView.layoutManager = GridLayoutManager(view.context, 3)
        searchMovieRecyclerView.adapter = movieSearchAdapter

        searchMovieEditText.setOnEditorActionListener(TextView.OnEditorActionListener { textView, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                searchViewModel.searchMovie(textView.text.toString())
                searchMovieEditText.clearFocus()
                showSoftKeyboard(false)
                return@OnEditorActionListener true
            }
            false
        })

        searchMovieEditText.requestFocus()
        showSoftKeyboard(true)
    }

    override fun onDestroyView() {
        super.onDestroyView()

        (view?.context?.applicationContext as App).releaseMovieSearchComponent()
    }

    override fun onClick(view: View) {
        val movieId = view.tag as Int
        val bundle = bundleOf(MovieDetailsFragment.MOVIE_ID to movieId)
        Navigation.findNavController(view).navigate(R.id.action_searchFragment_to_detailsFragment, bundle)
    }

    private fun showViewState(movieSearchViewState: MovieSearchViewState) {
        movieSearchViewState.movies?.let {
            movieSearchAdapter.submitList(it)
        }
    }

    private fun showSoftKeyboard(show: Boolean) {
        val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        if (show) {
            imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,0)
        } else {
            imm.hideSoftInputFromWindow(searchMovieEditText.windowToken,0)
        }
    }
}
