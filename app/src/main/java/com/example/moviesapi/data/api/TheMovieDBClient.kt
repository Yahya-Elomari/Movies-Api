package com.example.moviesapi.data.api

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.example.moviesapi.util.ConnectivityInterceptor

const val API_KEY = "e331ae30a57f3e63003ef83e5cf253cc"
const val BASE_URL = "https://api.themoviedb.org/3/"
const val POSTER_BASE_URL = "https://image.tmdb.org/t/p/w342"
const val FIRST_PAGE = 1
const val POST_PER_PAGE = 20

object TheMovieDBClient {

    fun getClient(connectivityInterceptor: ConnectivityInterceptor): TheMovieDBInterface {
        val requestInterceptor = Interceptor { chain ->
            // Interceptor take only one argument which is a lambda function so parenthesis can be omitted

            val url = chain.request()
                .url
                .newBuilder()
                .addQueryParameter("api_key", API_KEY)
                .build()
            val request = chain.request()
                .newBuilder()
                .url(url)
                .build()

            return@Interceptor chain.proceed(request)   //explicitly return a value from whit @ annotation. lambda always returns the value of the last expression implicitly
        }

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(requestInterceptor)
            //.addInterceptor(connectivityInterceptor)
//            .readTimeout(60, TimeUnit.SECONDS)
//            .connectTimeout(60, TimeUnit.SECONDS)
            .build()

        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TheMovieDBInterface::class.java)  // this line of code can be removed and added in the part where it is instantiated
    }
}