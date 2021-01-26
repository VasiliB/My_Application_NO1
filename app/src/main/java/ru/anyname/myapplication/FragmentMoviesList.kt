package ru.anyname.myapplication

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_movies_details.*
import kotlinx.android.synthetic.main.fragment_movies_list.*
import ru.anyname.myapplication.data.models.Movie
import ru.anyname.myapplication.databinding.FragmentMoviesListBinding
import ru.anyname.myapplication.domain.MoviesDataSource


class FragmentMoviesList : Fragment(R.layout.view_holder_movie) {

    private var recycler: RecyclerView? = null
    // Scoped to the lifecycle of the fragment's view (between onCreateView and onDestroyView)
    private var fragmentMovieslistBinding: FragmentMoviesListBinding? = null


//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        fragmentMovieslistBinding = FragmentMoviesListBinding.bind(view)
//
//        fragmentMovieslistBinding?.bgmovie?.setOnClickListener {
//            findNavController().navigate(R.id.action_FragmentMoviesList_to_FragmentMoviesDetails)
//        }
//    }

    override fun onDestroyView() {
        // Consider not storing the binding instance in a field
        // if not needed.
        fragmentMovieslistBinding = null
        super.onDestroyView()
    }
// Binding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recycler = view.findViewById(R.id.movieCover)
        //TODO 5: pass click listener to adapter
        recycler?.adapter = MoviesAdapter(clickListener)
    }

    override fun onStart() {
        super.onStart()

        updateData()
    }

    override fun onDetach() {
        recycler = null

        super.onDetach()
    }

    private fun updateData() {
        (recycler?.adapter as? MoviesAdapter)?.apply {
            bindMovies(MoviesDataSource().getMovies())
        }
    }

private fun doOnClick(movie: Movie) {
    recycler?.let { rv ->
        Snackbar.make(
            rv,
            getString(R.string.app_name),
            Snackbar.LENGTH_SHORT)
            .show()
    }
}

    /*TODO 4: create implementation of click listener
                you can call function
                doOnClick(actor: Actor)
     */
    private val clickListener = object : OnRecyclerItemClicked {
        override fun onClick(movie: Movie) {
            doOnClick(movie)
        }
    }

//    companion object {
//        fun newInstance() = MoviesAdapter()
//    }
}


