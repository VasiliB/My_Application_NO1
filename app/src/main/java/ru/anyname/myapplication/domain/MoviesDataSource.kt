package ru.anyname.myapplication.domain

import ru.anyname.myapplication.data.models.Movie


class  MoviesDataSource {
    fun getMovies(): List<Movie> {
        return listOf(
//            Movie("avengers", "+13", "like", "Action, Adventure, Drama", "4", "125 Reviews", "Avengers: End Game", "137 min"),
//            Movie("tenet", "+16", "like_1", "Action, Sci-Fi, Thriller ", "5", "98 Reviews", "Tenet", "97 min"),
//            Movie("black_widow", "+13", "like", "Action, Adventure, Sci-Fi", "4", "38 Reviews", "Black Widow", "102 min"),
//            Movie("wonder_woman_1984", "+13", "like", "Action, Adventure, Fantasy", "5", "74 Reviews", "Wonder Woman 1984", "120 min")
            Movie("https://image.ibb.co/nKNBrd/Alicia_Vikander.jpg", "+13", "Action, Adventure, Drama", "125 Reviews", "Avengers: End Game", "137 min"),
            Movie("src\\main\\res\\drawable\\tenet.webp", "+13", "Action, Sci-Fi, Thriller ", "98 Reviews", "Tenet", "97 min"),
            Movie("src\\main\\res\\drawable\\black_widow", "+16", "Action, Adventure, Sci-Fi", "38 Reviews", "Black Widow", "102 min"),
            Movie("src\\main\\res\\drawable\\wonder_woman_1984.webp", "+13", "Action, Adventure, Fantasy", "74 Reviews", "Wonder Woman 1984", "120 min")

        )
    }
}