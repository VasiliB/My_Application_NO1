package ru.anyname.myapplication.di

import ru.anyname.myapplication.data.MovieRepository


internal interface MovieRepositoryProvider {
    fun provideMovieRepository(): MovieRepository
}