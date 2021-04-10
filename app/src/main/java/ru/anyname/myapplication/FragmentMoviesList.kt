package ru.anyname.myapplication

import android.content.Context
import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.view_holder_movie.*
import kotlinx.coroutines.launch
import ru.anyname.myapplication.data.MovieRepositoryProvider
import ru.anyname.myapplication.data.models.Movie



class FragmentMoviesList : Fragment() {

    private var listener: MoviesListItemClickListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is MoviesListItemClickListener) {
            listener = context
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_movies_list, container, false)

    }
//    // Scoped to the lifecycle of the fragment's view (between onCreateView and onDestroyView)
//    private var fragmentMovieslistBinding: FragmentMoviesListBinding? = null
//
//    override fun onDestroyView() {
//        // Consider not storing the binding instance in a field
//        // if not needed.
//        fragmentMovieslistBinding = null
//        super.onDestroyView()
//    }
//// Binding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<RecyclerView>(R.id.rv_movies_list).apply {
            this.layoutManager = GridLayoutManager(this.context, 2)

            val adapter = MoviesAdapter { movieData ->
                listener?.onMovieSelected(movieData)
            }

            this.adapter = adapter

            loadDataToAdapter(adapter)
        }
    }

    private fun loadDataToAdapter(adapter: MoviesAdapter) {
        val repository = (requireActivity() as MovieRepositoryProvider).provideMovieRepository()
        lifecycleScope.launch {
            val moviesData = repository.loadMovies()

            adapter.submitList(moviesData)
        }
    }

    override fun onDetach() {
        listener = null

        super.onDetach()
    }

    interface MoviesListItemClickListener {
        fun onMovieSelected(movie: Movie)
    }

    companion object {
        fun create() = FragmentMoviesList()
    }

//class MarginItemDecoration(
//    private val spaceSize: Int,
//    private val spanCount: Int = 1,
//    private val orientation: Int = GridLayoutManager.VERTICAL
//) : RecyclerView.ItemDecoration() {
//    override fun getItemOffsets(
//        outRect: Rect, view: View,
//        parent: RecyclerView, state: RecyclerView.State
//    ) {
//        with(outRect) {
//            if (orientation == GridLayoutManager.VERTICAL) {
//                if (parent.getChildAdapterPosition(view) < spanCount) {
//                    top = spaceSize
//                }
//                if (parent.getChildAdapterPosition(view) % spanCount == 0) {
//                    left = spaceSize
//                }
//            } else {
//                if (parent.getChildAdapterPosition(view) < spanCount) {
//                    left = spaceSize
//                }
//                if (parent.getChildAdapterPosition(view) % spanCount == 0) {
//                    top = spaceSize
//                }
//            }
//
//            right = spaceSize
//            bottom = spaceSize
//        }
//    }
}

