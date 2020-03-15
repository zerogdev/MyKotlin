package com.mysample.disneymotions.view.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import com.mysample.disneymotions.base.LiveCoroutinesViewModel
import com.mysample.disneymotions.model.Poster
import com.mysample.disneymotions.repository.MainRepository
import com.skydoves.whatif.whatIfNotNull
import timber.log.Timber

class MainViewModel constructor(
    private val mainRepository: MainRepository
) : LiveCoroutinesViewModel() {

    private var posterFetchingLiveData : MutableLiveData<Boolean> = MutableLiveData()
    val posterListLiveData : LiveData<List<Poster>>

    val toastLiveData: MutableLiveData<String> = MutableLiveData()

    init {

        this.posterListLiveData = this.posterFetchingLiveData.switchMap {
            launchOnViewModelScope {
                Timber.tag("LYK").d("this.mainRepository.loadDisneyPosters { this.toastLiveData.postValue(it)")
                this.mainRepository.loadDisneyPosters { this.toastLiveData.postValue(it) }
            }
        }
    }

    fun fetchDisneyPosterList() = this.posterFetchingLiveData.postValue(true)
}