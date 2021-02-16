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
        // TODO 07: Refactor this method's body.
        //  When holder is the Empty Holder, show toast.
        //  When holder is the Data Holder, call public method of this holder and provide parameters.
        //  In our workshop, the position of the item inside the recycler is the same
        //  as the position inside our Actors collection.
        when (holder) {
            is DataViewHolder -> {
                holder.onBind(movies[position])
                holder.itemView.setOnClickListener {
                    val bundle = Bundle()
                    bundle.putParcelable(FragmentMoviesDetails.ARG_MOVIE, movies[position])
                    Navigation.findNavController(holder.itemView)
                        .navigate(R.id.action_FragmentMoviesList_to_FragmentMoviesDetails, bundle)
                }

            }
            is EmptyViewHolder -> {
                Toast.makeText(holder.itemView.context,
                    "Nothing to bind in default holder",
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
    // TODO 01: Open a file: "../res/layout/item_actors_data.xml".
    //  You can see three views: "actor_avatar, actor_name, actor_oscar_state".
    //  You have to update these view's content in runtime.
    //  First of all, find these views inside the "itemView" and store to variables.
    //  This "itemView" is a single recycler's item which is currently updating.
    private val cover: ImageView? = itemView.findViewById(R.id.movieCover)

    private val ageLimit: TextView? = itemView.findViewById(R.id.ageLimit)
    private val movieGenre: TextView? = itemView.findViewById(R.id.genre)
    private val reviews: TextView? = itemView.findViewById(R.id.reviews)
    private val movieTitle: TextView? = itemView.findViewById(R.id.title)
    private val movieDuration: TextView? = itemView.findViewById(R.id.duration)

    // TODO 02: Rename this public method. Provide some data class into this method's parameters.
    //  This data class should contains actor's avatar url, name and if actor has oscar or not.
    fun onBind(movie: Movie) {
        // TODO 03_01: Load an avatar picture.

//        Glide.with(context)
//            .load(movie.cover)
//            .into(cover)
        cover?.setImageDrawable(context.getDrawable(getImage(movie.cover)))


//        Glide.with(context)
//            .load(movie.cover)
//            .into(cover)


//        // TODO 03_02: Setup new name.
//
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

// TODO 04: Create two Int constants for different ViewTypes.
//  First represents the Empty View Holder. Second represents a ViewHolder with data.

private val VIEW_TYPE_EMPTY = 0
private val VIEW_TYPE_ACTORS = 1


