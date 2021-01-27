package ru.anyname.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import ru.anyname.myapplication.data.models.Movie

class MoviesAdapter : RecyclerView.Adapter<MoviesViewHolder>() {

//     Do not change.
    private val imageOption = RequestOptions()
        .placeholder(R.drawable.ic_avatar_placeholder)
        .fallback(R.drawable.ic_avatar_placeholder)
        .circleCrop()

    private var movies = listOf<Movie>()

    override fun getItemViewType(position: Int): Int {
        // TODO 05: Change the default return result, and return constant from the previous step.
        //  If actors are empty, return the constant for the EmptyViewHolder.
        //  Otherwise return the constant for the DataViewHolder.
        return when (movies.size) {
            0 -> VIEW_TYPE_EMPTY
            else -> VIEW_TYPE_ACTORS
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        // TODO 06: You defined two different view types.
        //  Change the existed return result.
        //  Return the EmptyViewHolder or DataViewHolder depending on the viewType.
        return when (viewType) {
            VIEW_TYPE_EMPTY -> EmptyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_actors_empty, parent, false))
            else -> DataViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.view_holder_movie, parent, false))
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
                holder.onBind(imageOption, movies[position])
            }
            is EmptyViewHolder -> {
                Toast.makeText(holder.itemView.context, "Nothing to bind in default holder", Toast.LENGTH_LONG).show()
            }
        }
        //Toast.makeText(holder.itemView.context, "Nothing to bind in default holder", Toast.LENGTH_LONG).show()
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
//    private val name: TextView? = itemView.findViewById(R.id.tv_actor_name)
    private val ageLimit: TextView? = itemView.findViewById(R.id.ageLimit)

    // TODO 02: Rename this public method. Provide some data class into this method's parameters.
    //  This data class should contains actor's avatar url, name and if actor has oscar or not.
    fun onBind( options : RequestOptions, movie: Movie) {
        // TODO 03_01: Load an avatar picture.
        Glide.with(context)
            .load(movie.movieCover)
            .apply(options)
            .into(cover)


//        // TODO 03_02: Setup new name.
//
        ageLimit?.text = movie.ageLimit
//
//        // TODO 03_03: Setup oscar state.
//        oscarState?.text = context.getString(R.string.fragment_actors_avatar_oscar_state_text, actor.hasOscar.toString())
    }
}

private val RecyclerView.ViewHolder.context
    get() = this.itemView.context

// TODO 04: Create two Int constants for different ViewTypes.
//  First represents the Empty View Holder. Second represents a ViewHolder with data.

private val VIEW_TYPE_EMPTY = 0
private val VIEW_TYPE_ACTORS = 1
