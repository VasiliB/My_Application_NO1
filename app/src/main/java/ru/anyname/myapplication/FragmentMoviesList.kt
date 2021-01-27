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


class FragmentMoviesList : Fragment(R.layout.fragment_movies_list) {

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
        recycler = view.findViewById(R.id.rv_movies_list)
        recycler?.adapter = MoviesAdapter()
    }

    override fun onStart() {
        super.onStart()

        updateData()
    }

    private fun updateData() {
        (recycler?.adapter as? MoviesAdapter)?.apply {
            bindMovies(MoviesDataSource().getMovies())
        }
    }

    companion object {
        fun newInstance() = FragmentMoviesList()
    }
}


