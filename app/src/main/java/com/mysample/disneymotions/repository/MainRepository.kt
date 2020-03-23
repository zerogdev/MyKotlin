package com.mysample.disneymotions.repository

import androidx.lifecycle.MutableLiveData
import com.mysample.disneymotions.model.Poster
import com.mysample.disneymotions.network.ApiResponse
import com.mysample.disneymotions.network.DisneyClient
import com.mysample.disneymotions.network.message
import com.mysample.disneymotions.persistence.PosterDao
import com.skydoves.whatif.whatIfNotNullOrEmpty
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

class MainRepository constructor(
    private val disneyClient: DisneyClient,
    private val posterDao: PosterDao
) : Repository {

    override var isLoading: Boolean = false

    init {
        Timber.d("Injection MainRepository")
    }

    suspend fun loadDisneyPosters(error: (String) -> Unit) :MutableLiveData<List<Poster>> = withContext(Dispatchers.IO) {
        val liveData = MutableLiveData<List<Poster>>()
        var posters = posterDao.getPosterList()
        if (posters.isEmpty()) {
            isLoading = true
            disneyClient.fetchDisneyPosters { response ->
                isLoading = false
                when(response) {
                    is ApiResponse.Success -> {
                        response.data.whatIfNotNullOrEmpty {
                            posters = it
                            liveData.postValue(it)
                            posterDao.insertPosterList(it)
                        }
                    }

                    is ApiResponse.Failure.Error -> error(response.message())
                    is ApiResponse.Failure.Exception -> error(response.message())
                }
            }
        }
        liveData.apply {
            Timber.tag("zerog").d("loadDisneyPosters-postValue(posters)")
            postValue(posters)
        }
    }
}