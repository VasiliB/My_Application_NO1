package ru.anyname.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.anyname.myapplication.data.models.Movie
import ru.anyname.myapplication.databinding.FragmentMoviesDetailsBinding
import ru.anyname.myapplication.domain.ActorsDataSource

class FragmentMoviesDetails : Fragment(R.layout.fragment_movies_details) {

    private var recycler: RecyclerView? = null

    private var fragmentMoviesDetailsBinding: FragmentMoviesDetailsBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movies_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentMoviesDetailsBinding = FragmentMoviesDetailsBinding.bind(view)
        fragmentMoviesDetailsBinding?.backsign?.setOnClickListener {
            findNavController().navigate(R.id.action_FragmentMoviesDetails_to_FragmentMoviesList)
        }

        recycler = view.findViewById(R.id.rv_movies_details)
        recycler?.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        recycler?.adapter = ActorsAdapter()
    }

    override fun onStart() {
        super.onStart()
        updateData()
    }

    private fun updateData() {
        (recycler?.adapter as? ActorsAdapter)?.apply {
            bindActors(ActorsDataSource().getActors())
        }
    }
}
