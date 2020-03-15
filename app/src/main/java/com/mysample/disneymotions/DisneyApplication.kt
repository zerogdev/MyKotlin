package com.mysample.disneymotions

import android.app.Application
import com.mysample.disneymotions.di.networkModule
import com.mysample.disneymotions.di.persistenceModule
import com.mysample.disneymotions.di.repositoryModule
import com.mysample.disneymotions.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class DisneyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        startKoin {
            Timber.tag("LYK").d("application")
            androidContext(this@DisneyApplication)
            Timber.tag("LYK").d("networkModule")
            modules(networkModule)
            Timber.tag("LYK").d("viewModelModule")

            Timber.tag("LYK").d("repositoryModule")
            modules(repositoryModule)
            Timber.tag("LYK").d("persistenceModule")
            modules(persistenceModule)
            modules(viewModelModule)
        }


    }
}