package ru.anyname.myapplication.data.models

data class Movie (
    val movieCover: String,
    val ageLimit: String,
//    val heart: String,
//    val movieGenre: String,
//    val movieRating: String,
//    val reviews: String,
//    val movieTitle: String,
//    val movieDuration: String
)

data class Actor (
    val actorName: String,
    val actorAvatar: String
)