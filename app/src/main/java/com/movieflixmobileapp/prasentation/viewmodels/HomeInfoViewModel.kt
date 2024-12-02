package com.movieflixmobileapp.prasentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.movieflix.domain.model.HomeFeedData
import com.movieflixmobileapp.core.utils.NetworkResults
import com.movieflixmobileapp.domine.usecases.GetMovieInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeInfoViewModel @Inject constructor(private val getMovieInfo: GetMovieInfo) {
    private var _homeFeedList: MutableLiveData<NetworkResults<HomeFeedData>> = MutableLiveData()
    var homeFeedList: LiveData<NetworkResults<HomeFeedData>> = _homeFeedList



}
