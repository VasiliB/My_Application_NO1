package ru.anyname.myapplication.movies

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.widget.ImageViewCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import ru.anyname.myapplication.R
import ru.anyname.myapplication.data.models.Movie

class MoviesAdapter(private val onClickCard: (item: Movie) -> Unit) :
    ListAdapter<Movie, MoviesAdapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.view_holder_movie, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, onClickCard)
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val movieImage: ImageView = itemView.findViewById(R.id.movieCover)

        private val pgText: TextView = itemView.findViewById(R.id.ageLimit)
        private val genreText: TextView? = itemView.findViewById(R.id.genre)
        private val likeImage: ImageView? = itemView.findViewById(R.id.like)
        private val reviewsText: TextView? = itemView.findViewById(R.id.reviews)
        private val titleText: TextView? = itemView.findViewById(R.id.title)
        private val movieLenText: TextView? = itemView.findViewById(R.id.duration)
        private val starsImages: List<ImageView> = listOf(
            itemView.findViewById(R.id.staricon1),
            itemView.findViewById(R.id.staricon2),
            itemView.findViewById(R.id.staricon3),
            itemView.findViewById(R.id.staricon4),
            itemView.findViewById(R.id.staricon0)
        )

        fun bind(item: Movie, onClickCard: (item: Movie) -> Unit) {
            movieImage.load(item.imageUrl)

            val context = itemView.context
            pgText.text =
                context.getString(R.string.movies_list_allowed_age_template, item.pgAge)
            if (genreText != null) {
                genreText.text = item.genres.joinToString { it.name }
            }
            if (reviewsText != null) {
                reviewsText.text =
                    context.getString(R.string.movies_list_reviews_template, item.reviewCount)
            }
            if (titleText != null) {
                titleText.text = item.title
            }
            if (movieLenText != null) {
                movieLenText.text = context.getString(R.string.movies_list_film_time, item.runningTime)
            }

            val likeColor = if (item.isLiked) {
                R.color.pink_light
            } else {
                R.color.color_white
            }
            if (likeImage != null) {
                ImageViewCompat.setImageTintList(
                    likeImage, ColorStateList.valueOf(
                        ContextCompat.getColor(likeImage.context, likeColor)
                    )
                )
            }

            //set stars tint
            starsImages.forEachIndexed { index, imageView ->
                val colorId = if (item.rating > index) R.color.pink_light else R.color.gray_dark
                ImageViewCompat.setImageTintList(
                    imageView, ColorStateList.valueOf(
                        ContextCompat.getColor(imageView.context, colorId)
                    )
                )
            }

            itemView.setOnClickListener {
                onClickCard(item)
            }
        }
    }


    class DiffCallback : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }
    }

}






