package com.example.moviesapi.ui.single_movie_details

import androidx.lifecycle.LiveData
import com.example.moviesapi.data.api.TheMovieDBInterface
import com.example.moviesapi.data.repository.MovieDetailsNetworkDataSource
import com.example.moviesapi.data.repository.NetworkState
import io.reactivex.rxjava3.disposables.CompositeDisposable

class MovieDetailsRepository  (private val apiService : TheMovieDBInterface) {

    lateinit var movieDetailsNetworkDataSource: MovieDetailsNetworkDataSource

    fun fetchSingleMovieDetails (compositeDisposable: CompositeDisposable, movieId: Int) : LiveData<MovieDetails> {

        movieDetailsNetworkDataSource = MovieDetailsNetworkDataSource(apiService,compositeDisposable)
        movieDetailsNetworkDataSource.fetchMovieDetails(movieId)

        return movieDetailsNetworkDataSource.downloadedMovieResponse

    }

    fun getMovieDetailsNetworkState(): LiveData<NetworkState> {
        return movieDetailsNetworkDataSource.networkState
    }
}