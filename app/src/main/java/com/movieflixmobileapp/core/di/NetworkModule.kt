package com.movieflixmobileapp.core.di

import android.content.Context
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit


@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    fun provideNetworkClient(@ApplicationContext context: Context): OkHttpClient{
        return OkHttpClient().newBuilder()
            .cache(Cache(context.cacheDir,(5 * 1024 * 1024).toLong())) // this will save the response from request in cache, so the client
            // don't have to send request everytime
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY // this is used to get the debug information when request is sent and the also
                    // provides the data of the response
                }
            )
            .addInterceptor{chain ->
                val request = chain.request().newBuilder()
                val  originalHttpUrl = chain.request().url

                val url = originalHttpUrl.newBuilder()
                    .addQueryParameter("api_key", "BuildConxfig.MOVIE_API_KEY")
                    .build()
                request.url(url)
                return@addInterceptor chain.proceed(request.build())

            }.addInterceptor{chain ->

                var request = chain.request()

                request = request.newBuilder().header("Cache-Control", "public, max-age=" + 60 * 5)
                    .build()
                chain.proceed(request)

            }
            .connectTimeout(10, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .readTimeout(10, TimeUnit.SECONDS)
            .build()


    }
}