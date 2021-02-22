package ru.anyname.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import ru.anyname.myapplication.data.models.Movie

class MoviesAdapter() : RecyclerView.Adapter<MoviesViewHolder>() {

    private var movies = listOf<Movie>()

    override fun getItemViewType(position: Int): Int {
        return when (movies.size) {
            0 -> VIEW_TYPE_EMPTY
            else -> VIEW_TYPE_ACTORS
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        return when (viewType) {
            VIEW_TYPE_EMPTY -> EmptyViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.item_actors_empty, parent, false))
            else -> DataViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.view_holder_movie, parent, false))
        }
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {

        when (holder) {
            is DataViewHolder -> {
                holder.onBind(movies[position])
                holder.itemView.setOnClickListener {
                    val bundle = Bundle()
//                    bundle.putParcelable(FragmentMoviesDetails.ARG_MOVIE, movies[position])
                    Navigation.findNavController(holder.itemView)
                        .navigate(R.id.action_FragmentMoviesList_to_FragmentMoviesDetails, bundle)
                }

            }
            is EmptyViewHolder -> {
                Toast.makeText(holder.itemView.context,
                    "Nothing to bind",
                    Toast.LENGTH_LONG).show()
            }

        }
    }

    override fun getItemCount(): Int = movies.size

    fun bindMovies(newMovies: List<Movie>) {
        movies = newMovies
    }
}

abstract class MoviesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

private class EmptyViewHolder(itemView: View) : MoviesViewHolder(itemView)
private class DataViewHolder(itemView: View) : MoviesViewHolder(itemView) {

    private val cover: ImageView? = itemView.findViewById(R.id.movieCover)

    private val ageLimit: TextView? = itemView.findViewById(R.id.ageLimit)
    private val movieGenre: TextView? = itemView.findViewById(R.id.genre)
    private val like: ImageView? = itemView.findViewById(R.id.like)
    private val rating1: ImageView? = itemView.findViewById(R.id.staricon1)
    private val rating2: ImageView? = itemView.findViewById(R.id.staricon2)
    private val rating3: ImageView? = itemView.findViewById(R.id.staricon3)
    private val rating4: ImageView? = itemView.findViewById(R.id.staricon4)
    private val rating5: ImageView? = itemView.findViewById(R.id.staricon0)
    private val reviews: TextView? = itemView.findViewById(R.id.reviews)
    private val movieTitle: TextView? = itemView.findViewById(R.id.title)
    private val movieDuration: TextView? = itemView.findViewById(R.id.duration)

    fun onBind(movie: Movie) {

        cover?.setImageDrawable(context.getDrawable(getImage(movie.cover)))

        like?.setImageDrawable(context.getDrawable(getImage(movie.like)))
        rating1?.setImageDrawable(context.getDrawable(getImage(movie.rating1)))
        rating2?.setImageDrawable(context.getDrawable(getImage(movie.rating2)))
        rating3?.setImageDrawable(context.getDrawable(getImage(movie.rating3)))
        rating4?.setImageDrawable(context.getDrawable(getImage(movie.rating4)))
        rating5?.setImageDrawable(context.getDrawable(getImage(movie.rating5)))

        ageLimit?.text = movie.ageLimit
        movieGenre?.text = movie.movieGenre
        reviews?.text = movie.reviews
        movieTitle?.text = movie.movieTitle
        movieDuration?.text = movie.movieDuration
    }

    fun getImage(imageName: String?): Int {
        return context.resources.getIdentifier(imageName, "drawable", context.packageName)
    }
}

private val RecyclerView.ViewHolder.context
    get() = this.itemView.context

private val VIEW_TYPE_EMPTY = 0
private val VIEW_TYPE_ACTORS = 1




