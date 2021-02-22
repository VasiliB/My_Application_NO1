package ru.anyname.myapplication

import android.graphics.Rect
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

        recycler = view.findViewById(R.id.rv_movies_list)
        recycler?.layoutManager = GridLayoutManager(context, 2)
        recycler?.adapter = MoviesAdapter()

//        recycler?.addItemDecoration(
//            MarginItemDecoration(8)
//        )
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
}

class MarginItemDecoration(
    private val spaceSize: Int,
    private val spanCount: Int = 1,
    private val orientation: Int = GridLayoutManager.VERTICAL
) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect, view: View,
        parent: RecyclerView, state: RecyclerView.State
    ) {
        with(outRect) {
            if (orientation == GridLayoutManager.VERTICAL) {
                if (parent.getChildAdapterPosition(view) < spanCount) {
                    top = spaceSize
                }
                if (parent.getChildAdapterPosition(view) % spanCount == 0) {
                    left = spaceSize
                }
            } else {
                if (parent.getChildAdapterPosition(view) < spanCount) {
                    left = spaceSize
                }
                if (parent.getChildAdapterPosition(view) % spanCount == 0) {
                    top = spaceSize
                }
            }

            right = spaceSize
            bottom = spaceSize
        }
    }
}

