package ru.anyname.myapplication.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.anyname.myapplication.data.MovieRepository


class MovieListViewModelFactory(private val repository: MovieRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T = MoviesListViewModel(repository) as T
}