package com.mysample.disneymotions.di

import com.mysample.disneymotions.view.ui.main.MainViewModel
import com.mysample.disneymotions.view.ui.main.details.PosterDetailViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import timber.log.Timber

val viewModelModule = module {
    viewModel {
        Timber.tag("LYK").d("viewModelModule-MainViewModel(get()")
        MainViewModel(get()) }

    viewModel {
        PosterDetailViewModel(get())
    }
}