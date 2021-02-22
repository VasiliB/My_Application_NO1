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
import ru.anyname.myapplication.data.models.Actor

class ActorsAdapter() : RecyclerView.Adapter<ActorsViewHolder>() {

    private var actors = listOf<Actor>()

    override fun getItemViewType(position: Int): Int {
        return when (actors.size) {
            0 -> VIEW_TYPE_EMPTY
            else -> VIEW_TYPE_ACTORS
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorsViewHolder {
        return when (viewType) {
            VIEW_TYPE_EMPTY -> EmptyViewHolderA(LayoutInflater.from(parent.context)
                .inflate(R.layout.item_actors_empty, parent, false))
            else -> DataViewHolderA(LayoutInflater.from(parent.context)
                .inflate(R.layout.view_holder_actor, parent, false))
        }
    }

    override fun onBindViewHolder(holder: ActorsViewHolder, position: Int) {
        when (holder) {
            is DataViewHolderA -> {
                holder.onBind(actors[position])
                holder.itemView.setOnClickListener {
                }
            }
            is EmptyViewHolderA -> {
                Toast.makeText(holder.itemView.context,
                    "Nothing to bind",
                    Toast.LENGTH_LONG).show()
            }

        }
    }

    override fun getItemCount(): Int = actors.size

    fun bindActors(newActors: List<Actor>) {
        actors = newActors
    }
}

abstract class ActorsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

private class EmptyViewHolderA(itemView: View) : ActorsViewHolder(itemView)
private class DataViewHolderA(itemView: View) : ActorsViewHolder(itemView) {
    private val actorAvatar: ImageView? = itemView.findViewById(R.id.actorAvatar)

    private val actorName: TextView? = itemView.findViewById(R.id.actorName)

    fun onBind(actor: Actor) {
        actorAvatar?.setImageDrawable(context.getDrawable(getImage(actor.actorAvatar)))
        actorName?.text = actor.actorName
    }

    fun getImage(imageName: String?): Int {
        return context.resources.getIdentifier(imageName, "drawable", context.packageName)
    }
}

private val RecyclerView.ViewHolder.context
    get() = this.itemView.context

private val VIEW_TYPE_EMPTY = 0
private val VIEW_TYPE_ACTORS = 1


