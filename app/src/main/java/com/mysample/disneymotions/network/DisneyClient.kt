package com.mysample.disneymotions.network

import com.mysample.disneymotions.model.Poster

class DisneyClient(private val disneyService: DisneyService) {

    fun fetchDisneyPosters(
        onResult: (response: ApiResponse<List<Poster>>) -> Unit
    ) {
        this.disneyService.fetchDisneyPosterList().transform(onResult)
    }
}