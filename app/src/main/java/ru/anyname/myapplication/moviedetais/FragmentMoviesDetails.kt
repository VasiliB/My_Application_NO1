package ru.anyname.myapplication.moviedetais

import android.content.Context
import android.content.res.ColorStateList
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.core.widget.ImageViewCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.load
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ru.anyname.myapplication.R
import ru.anyname.myapplication.data.models.Movie
import ru.anyname.myapplication.di.MovieRepositoryProvider

class FragmentMoviesDetails : Fragment() {

    private val viewModel: MovieDetailsViewModel by viewModels {
        MovieDetailsViewModelFactory((requireActivity() as MovieRepositoryProvider).provideMovieRepository())
    }

    private var listener: MovieDetailsBackClickListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is MovieDetailsBackClickListener) {
            listener = context
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movies_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val movieId = arguments?.getSerializable(PARAM_MOVIE_ID) as? Int ?: return

        view.findViewById<RecyclerView>(R.id.rv_movies_details).apply {

            this.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)

            this.adapter = ActorsAdapter()
        }

        view.findViewById<View>(R.id.backsign)?.setOnClickListener {
            listener?.onMovieDeselected()
        }

        viewModel.loadDetails(movieId)

        lifecycleScope.launch {
            viewModel.movie.collect { movie ->
                movie?.let { bindUI(view, it) } ?: showMovieNotFoundError()
            }
        }

    }

    private fun showMovieNotFoundError() {
        Toast.makeText(requireContext(),
            R.string.error_movie_not_found,
            Toast.LENGTH_LONG)
            .show()
    }

    private fun bindUI(view: View, movie: Movie) {
        updateMovieDetailsInfo(movie)
        val adapter =
            view.findViewById<RecyclerView>(R.id.rv_movies_details).adapter as ActorsAdapter
        adapter.submitList(movie.actors)
    }

    override fun onDetach() {
        listener = null

        super.onDetach()
    }

    private fun updateMovieDetailsInfo(movie: Movie) {
        view?.findViewById<ImageView>(R.id.orig)?.load(movie.detailImageUrl)

        view?.findViewById<TextView>(R.id.limit13)?.text =
            context?.getString(R.string.movies_list_allowed_age_template, movie.pgAge)

        view?.findViewById<TextView>(R.id.title)?.text = movie.title
        view?.findViewById<TextView>(R.id.genre)?.text =
            movie.genres.joinToString { it.name }
        view?.findViewById<TextView>(R.id.reviews)?.text =
            context?.getString(R.string.movies_list_reviews_template, movie.reviewCount)
        view?.findViewById<TextView>(R.id.story)?.text = movie.storyLine

        val starsImages = listOf<ImageView?>(
            view?.findViewById(R.id.staricon1),
            view?.findViewById(R.id.staricon2),
            view?.findViewById(R.id.staricon3),
            view?.findViewById(R.id.staricon4),
            view?.findViewById(R.id.staricon0)
        )
        starsImages.forEachIndexed { index, imageView ->
            imageView?.let {
                val colorId =
                    if (movie.rating > index) R.color.pink_light else R.color.gray_dark
                ImageViewCompat.setImageTintList(
                    imageView, ColorStateList.valueOf(
                        ContextCompat.getColor(imageView.context, colorId)
                    )
                )
            }
        }
    }

    interface MovieDetailsBackClickListener {
        fun onMovieDeselected()
    }

    companion object {
        private const val PARAM_MOVIE_ID = "movie_id"

        fun create(movieId: Int) = FragmentMoviesDetails().also {
            val args = bundleOf(
                PARAM_MOVIE_ID to movieId
            )
            it.arguments = args
        }
    }
}
