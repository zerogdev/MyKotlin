package com.mysample.disneymotions.repository

import com.mysample.disneymotions.persistence.PosterDao

class DetailRepository constructor(
    private val posterDao: PosterDao
) : Repository{

    override var isLoading: Boolean
        = false

    fun getPosterById(id: Long) = posterDao.getPoster(id)
}