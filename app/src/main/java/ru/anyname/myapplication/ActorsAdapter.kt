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
        // TODO 07: Refactor this method's body.
        //  When holder is the Empty Holder, show toast.
        //  When holder is the Data Holder, call public method of this holder and provide parameters.
        //  In our workshop, the position of the item inside the recycler is the same
        //  as the position inside our Actors collection.
        when (holder) {
            is DataViewHolderA -> {
                holder.onBind(actors[position])
                holder.itemView.setOnClickListener {
                    val bundle = Bundle()
                    bundle.putParcelable(FragmentMoviesDetails.ARG_MOVIE, actors[position])

                }

            }
            is EmptyViewHolderA -> {
                Toast.makeText(holder.itemView.context,
                    "Nothing to bind in default holder",
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
    // TODO 01: Open a file: "../res/layout/item_actors_data.xml".
    //  You can see three views: "actor_avatar, actor_name, actor_oscar_state".
    //  You have to update these view's content in runtime.
    //  First of all, find these views inside the "itemView" and store to variables.
    //  This "itemView" is a single recycler's item which is currently updating.
    private val actorAvatar: ImageView? = itemView.findViewById(R.id.actorAvatar)

    private val actorName: TextView? = itemView.findViewById(R.id.actorName)


    // TODO 02: Rename this public method. Provide some data class into this method's parameters.
    //  This data class should contains actor's avatar url, name and if actor has oscar or not.
    fun onBind(actor: Actor) {
        // TODO 03_01: Load an avatar picture.

        actorAvatar?.setImageDrawable(context.getDrawable(getImage(actor.actorAvatar)))


//        // TODO 03_02: Setup new name.
//
        actorName?.text = actor.actorName

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


