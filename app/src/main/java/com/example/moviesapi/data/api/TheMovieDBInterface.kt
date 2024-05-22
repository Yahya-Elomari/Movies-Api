package com.example.moviesapi.data.api

import com.example.moviesapi.data.vo.MovieDetails
import com.example.moviesapi.data.vo.MovieResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TheMovieDBInterface {
//    https://api.themoviedb.org/3/movie/popular?api_key=e331ae30a57f3e63003ef83e5cf253cc
//    https://api.themoviedb.org/3/movie/{movie_id}?api_key=e331ae30a57f3e63003ef83e5cf253cc
//    https://api.themoviedb.org/3/

    @GET("movie/popular")  //upcoming , popular
    fun getPopularMovie(
        @Query("page") page: Int
    ): Single<MovieResponse>


    @GET("movie/{movie_id}")  //upcoming , popular
    fun getMovieDetails(
        @Path("movie_id") id: Int
    ): Single<MovieDetails>

}