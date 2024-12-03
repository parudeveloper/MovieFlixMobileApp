package com.movieflixmobileapp.data.repository

import android.app.Application
import android.util.Log
import com.example.movieflix.domain.model.HomeFeedData
import com.example.movieflix.domain.model.MovieList
import com.example.movieflix.domain.model.MovieVideoResultList
import com.example.movieflix.domain.model.WatchProviders
import com.movieflixmobileapp.core.utils.Constants
import com.movieflixmobileapp.core.utils.NetworkResults
import com.movieflixmobileapp.data.model.HomeFeedResponse
import com.movieflixmobileapp.data.remote.RemoteDataSource
import com.movieflixmobileapp.domine.repository.MovieInfoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import java.io.IOException

class MovieInfoRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val appContext: Application
) : MovieInfoRepository {
    override fun getHomeFeedData(): Flow<NetworkResults<HomeFeedData>> = flow {
        emit(NetworkResults.Loading())
        try {

            withContext(Dispatchers.IO){
                val upcomingMovieListDef = async { remoteDataSource.getUpcomingMovies() }

                val wholeMoviesList= mutableListOf<HomeFeedResponse>()

                val upcomingMovieList = upcomingMovieListDef.await()
                Log.i("MovieInfoRepositoryImpl",upcomingMovieList.body()?.results.toString())
                wholeMoviesList.add(HomeFeedResponse(Constants.UPCOMING_MOVIES, upcomingMovieList.body()?.results!!))

            }
        }catch (e:Exception){
            when(e){
                is IOException -> {
                    emit(NetworkResults.Error("Check ur internet connection"))
                }
                else ->{
                    emit(NetworkResults.Error(e.message ?: "Something went wrong"))
                }
            }
        }
    }

    override fun getMovieTrailer(movieId: Int): Flow<NetworkResults<MovieVideoResultList>> {
        TODO("Not yet implemented")
    }

    override fun getRecommendation(movieId: Int): Flow<NetworkResults<MovieList>> {
        TODO("Not yet implemented")
    }

    override fun getWhereToWatchProvider(movieId: Int): Flow<NetworkResults<WatchProviders>> {
        TODO("Not yet implemented")
    }
}