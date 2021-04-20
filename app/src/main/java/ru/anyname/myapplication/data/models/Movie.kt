package ru.anyname.myapplication.data.models

import ru.anyname.myapplication.data.models.Actor
import ru.anyname.myapplication.data.models.Genre

data class Movie(
    val id: Int,
    val pgAge: Int,
    val title: String,
    val genres: List<Genre>,
    val runningTime: Int,
    val reviewCount: Int,
    val isLiked: Boolean,
    val rating: Int,
    val imageUrl: String,
    val detailImageUrl: String,
    val storyLine: String,
    val actors: List<Actor>,
)