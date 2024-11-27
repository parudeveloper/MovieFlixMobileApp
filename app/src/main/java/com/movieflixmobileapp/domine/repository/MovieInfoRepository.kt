package com.movieflixmobileapp.domine.repository

import com.example.movieflix.domain.model.HomeFeedData
import com.example.movieflix.domain.model.MovieList
import com.example.movieflix.domain.model.MovieVideoResultList
import com.example.movieflix.domain.model.WatchProviders
import com.movieflixmobileapp.core.utils.NetworkResults
import kotlinx.coroutines.flow.Flow

interface MovieInfoRepository {
    fun getHomeFeedData(): Flow<NetworkResults<HomeFeedData>>

    fun getMovieTrailer(movieId:Int): Flow<NetworkResults<MovieVideoResultList>>

    fun getRecommendation(movieId:Int): Flow<NetworkResults<MovieList>>

    fun getWhereToWatchProvider(movieId:Int): Flow<NetworkResults<WatchProviders>>



}