package ru.anyname.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.anyname.myapplication.data.models.Movie

class MoviesAdapter(
    private val clickListener: OnRecyclerItemClicked
) : RecyclerView.Adapter<MoviesViewHolder>() {

    private var movies = listOf<Movie>()

    override fun getItemViewType(position: Int): Int {
        return when (movies.size) {
            0 -> VIEW_TYPE_EMPTY
            else -> VIEW_TYPE_ACTORS
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        return when (viewType) {
            VIEW_TYPE_EMPTY -> EmptyViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.support_simple_spinner_dropdown_item, parent, false)
            )
            else -> DataViewHolder(
                LayoutInflater.from(
                    parent.context
                ).inflate(R.layout.view_holder_movie, parent, false)
            )
        }
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        when (holder) {
            is DataViewHolder -> {
                holder.onBind(movies[position])
                //TODO 3: set onClick listener to binded view
                holder.itemView.setOnClickListener {
                    clickListener.onClick(movies[position])
                }
            }
            is EmptyViewHolder -> { /* nothing to bind */ }
        }
    }

    override fun getItemCount(): Int = movies.size

    fun bindMovies(newMovies: List<Movie>) {
        movies = newMovies
        notifyDataSetChanged()
    }
}

abstract class MoviesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

private class EmptyViewHolder(itemView: View) : MoviesViewHolder(itemView)
private class DataViewHolder(itemView: View) : MoviesViewHolder(itemView) {

    private val movieCover: ImageView = itemView.findViewById(R.id.movieCover)
//    private val ageLimit: TextView = itemView.findViewById(R.id.tv_actor_name)
//    private val heart: TextView = itemView.findViewById(R.id.tv_actor_oscar_state)
//    private val movieGenre: ImageView = itemView.findViewById(R.id.iv_actor_avatar)
//    private val movieRating: TextView = itemView.findViewById(R.id.tv_actor_name)
//    private val reviews: TextView = itemView.findViewById(R.id.tv_actor_oscar_state)
//    private val movieTitle: ImageView = itemView.findViewById(R.id.iv_actor_avatar)
//    private val movieDuration: TextView = itemView.findViewById(R.id.tv_actor_name)

    fun onBind(movie: Movie) {
        Glide.with(context)
            .load(movie.movieCover)
//            .apply(imageOption)
            .into(movieCover)

//        name.text = actor.name
//
//        oscarState.text = context.getString(
//            R.string.fragment_actors_avatar_oscar_state_text,
//            actor.hasOscar.toString()
//        )
    }

//    companion object {
//        private val imageOption = RequestOptions()
//            .placeholder(R.drawable.ic_avatar_placeholder)
//            .fallback(R.drawable.ic_avatar_placeholder)
//            .circleCrop()
//    }
}

private val RecyclerView.ViewHolder.context
    get() = this.itemView.context

private const val VIEW_TYPE_EMPTY = 0
private const val VIEW_TYPE_ACTORS = 1

/*TODO 1: create interface of clickListener with method
         fun onClick(actor: Actor)
*/
interface OnRecyclerItemClicked {
    fun onClick(movie: Movie)
}