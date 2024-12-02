package com.movieflixmobileapp.prasentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieflix.domain.model.HomeFeedData
import com.movieflixmobileapp.core.utils.NetworkResults
import com.movieflixmobileapp.domine.usecases.GetMovieInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeInfoViewModel @Inject constructor(private val getMovieInfo: GetMovieInfo) : ViewModel() {
    private var _homeFeedList: MutableLiveData<NetworkResults<HomeFeedData>> = MutableLiveData()
    var homeFeedList: LiveData<NetworkResults<HomeFeedData>> = _homeFeedList

    init {
        getMovieInfoData()
    }

    fun getMovieInfoData() {
        viewModelScope.launch {
            getMovieInfo.getMovieInfo().onEach {
                _homeFeedList.value = it
            }.launchIn(this)
        }
    }

}
