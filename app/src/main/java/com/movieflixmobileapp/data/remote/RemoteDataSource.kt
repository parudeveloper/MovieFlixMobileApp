package com.movieflixmobileapp.data.remote

import com.movieflixmobileapp.data.model.MovieResponseList
import com.movieflixmobileapp.data.network.ApiClient
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiClient: ApiClient) {
    suspend fun getUpcomingMovies(): Response<MovieResponseList> {
        return  apiClient.getUpcomingMoviesApiCall()
    }
}