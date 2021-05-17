package ru.anyname.myapplication.moviedetais

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.anyname.myapplication.data.MovieRepository

class MovieDetailsViewModelFactory(private val repository: MovieRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T = MovieDetailsViewModel(repository) as T
}