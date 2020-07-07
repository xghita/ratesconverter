package com.revolut.ratesconverter.main

import android.app.Application
import com.revolut.ratesconverter.main.di.ApplicationModule.databaseModule
import com.revolut.ratesconverter.main.di.ApplicationModule.networkModule
import com.revolut.ratesconverter.main.di.ApplicationModule.repositoryModule
import com.revolut.ratesconverter.main.di.ApplicationModule.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class RatesConverterApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@RatesConverterApplication)
            modules(
                networkModule,
                databaseModule,
                repositoryModule,
                viewModelModule
            )
        }
    }
}