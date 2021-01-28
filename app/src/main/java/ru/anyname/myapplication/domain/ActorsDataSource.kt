package ru.anyname.myapplication.domain

import ru.anyname.myapplication.data.models.Actor


class  ActorsDataSource {
    fun getActors(): List<Actor> {
        return listOf(
            Actor("Robert Downey Jr.", "movie1"),
            Actor("Chris\u2028Evans", "movie2"),
            Actor("Mark Ruffalo", "Movie3"),
            Actor("Chris Hemsworth", "Movie4")
        )
    }
}