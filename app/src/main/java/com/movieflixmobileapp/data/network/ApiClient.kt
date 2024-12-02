package com.movieflixmobileapp.data.network

import com.movieflixmobileapp.data.model.MovieResponseList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiClient {

    @GET("3/movie/upcoming")
    suspend fun getUpcomingMoviesApiCall(
        @Query("language") language:String? ="en-US",
        @Query("page") page:Int=1,
        @Query("region") region:String="US"
    ): Response<MovieResponseList>

}