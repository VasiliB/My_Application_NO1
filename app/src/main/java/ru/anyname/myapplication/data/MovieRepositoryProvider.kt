package ru.anyname.myapplication.data

internal interface MovieRepositoryProvider {
    fun provideMovieRepository(): MovieRepository
}