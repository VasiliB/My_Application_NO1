package ru.anyname.myapplication

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.view_holder_movie.*
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
                fragmentMovieslistBinding = FragmentMoviesListBinding.bind(view)

        fragmentMovieslistBinding?.root?.setOnClickListener {
            findNavController().navigate(R.id.action_FragmentMoviesList_to_FragmentMoviesDetails)
        }
//        root?.setOnClickListener(
//            Navigation.createNavigateOnClickListener(R.id.action_FragmentMoviesList_to_FragmentMoviesDetails)
//        )

        recycler = view.findViewById(R.id.rv_movies_list)
        recycler?.layoutManager = GridLayoutManager(context, 2)
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

    private fun doOnClick(navigate: Unit) {
        recycler?.let {
            findNavController().navigate(R.id.action_FragmentMoviesList_to_FragmentMoviesDetails)
        }
    }

    /*TODO 4: create implementation of click listener
                you can call function
                doOnClick(actor: Actor)
     */
//    private val clickListener = object : OnRecyclerItemClicked {
//        override fun onClick(view: View) {
//            view.setOnClickListener(
//                Navigation.createNavigateOnClickListener(R.id.action_FragmentMoviesList_to_FragmentMoviesDetails)
//            )
//        }


//            doOnClick(findNavController().navigate(R.id.action_FragmentMoviesList_to_FragmentMoviesDetails))
////        }
//    }

}


