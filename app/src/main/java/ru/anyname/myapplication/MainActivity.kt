package ru.anyname.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.anyname.myapplication.data.JsonMovieRepository
import ru.anyname.myapplication.data.MovieRepository
import ru.anyname.myapplication.data.models.Movie
import ru.anyname.myapplication.di.MovieRepositoryProvider
import ru.anyname.myapplication.moviedetais.FragmentMoviesDetails
import ru.anyname.myapplication.movies.FragmentMoviesList

class MainActivity : AppCompatActivity(),
    FragmentMoviesList.MoviesListItemClickListener,
    FragmentMoviesDetails.MovieDetailsBackClickListener,
    MovieRepositoryProvider {

    private val jsonMovieRepository = JsonMovieRepository(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            routeToMoviesList()
        }
    }

    override fun onMovieSelected(movie: Movie) {
        routeToMovieDetails(movie)
    }

    override fun onMovieDeselected() {
        routeBack()
    }

    private fun routeToMoviesList() {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.container,
                FragmentMoviesList.create(),
                FragmentMoviesList::class.java.simpleName
            )
            .addToBackStack("trans:${FragmentMoviesList::class.java.simpleName}")
            .commit()
    }

    private fun routeToMovieDetails(movie: Movie) {
        supportFragmentManager.beginTransaction()
            .add(
                R.id.container,
                FragmentMoviesDetails.create(movie.id),
                FragmentMoviesDetails::class.java.simpleName
            )
            .addToBackStack("trans:${FragmentMoviesDetails::class.java.simpleName}")
            .commit()
    }

    private fun routeBack() {
        supportFragmentManager.popBackStack()
    }

    override fun provideMovieRepository(): MovieRepository = jsonMovieRepository

//    private lateinit var navConroller: NavController
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//        navConroller = Navigation.findNavController(this, R.id.mainNavHostFragment)
//
//    }
}