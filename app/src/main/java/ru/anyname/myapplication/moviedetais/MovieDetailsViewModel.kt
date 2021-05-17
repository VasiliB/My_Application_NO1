package ru.anyname.myapplication.moviedetais

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.anyname.myapplication.data.MovieRepository
import ru.anyname.myapplication.data.models.Movie

class MovieDetailsViewModel(private val repository: MovieRepository) : ViewModel() {

    private val _movie = MutableStateFlow<Movie?>(null)
    val movie: StateFlow<Movie?> = _movie

    fun loadDetails(movieId: Int) {
        viewModelScope.launch {
            _movie.value = repository.loadMovie(movieId)
        }
    }
}