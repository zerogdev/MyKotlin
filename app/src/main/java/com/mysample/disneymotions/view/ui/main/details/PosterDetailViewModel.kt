package com.mysample.disneymotions.view.ui.main.details

import com.mysample.disneymotions.base.LiveCoroutinesViewModel
import com.mysample.disneymotions.repository.DetailRepository

class PosterDetailViewModel(
    private val repository: DetailRepository
) : LiveCoroutinesViewModel() {

    fun getPoster(id:Long) = repository.getPosterById(id)
}