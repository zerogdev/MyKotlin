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
            Timber.tag("zerog").d("application")
            androidContext(this@DisneyApplication)
            Timber.tag("zerog").d("networkModule")
            modules(networkModule)
            Timber.tag("zerog").d("viewModelModule")

            Timber.tag("zerog").d("repositoryModule")
            modules(repositoryModule)
            Timber.tag("zerog").d("persistenceModule")
            modules(persistenceModule)
            modules(viewModelModule)
        }


    }
}