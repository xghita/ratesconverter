package com.revolut.ratesconverter.main.di

import com.readystatesoftware.chuck.ChuckInterceptor
import com.revolut.ratesconverter.feature.ratesconverter.data.RatesConverterRepository
import com.revolut.ratesconverter.feature.ratesconverter.data.RatesConverterRepositoryImpl
import com.revolut.ratesconverter.feature.ratesconverter.domain.RatesConverterViewModel
import com.revolut.ratesconverter.main.network.ApiManager
import com.revolut.ratesconverter.main.network.ApiManagerImpl
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApplicationModule {
    val networkModule =
        module {
            single {
                OkHttpClient.Builder()
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .writeTimeout(30, TimeUnit.SECONDS)
                    .addInterceptor(ChuckInterceptor(androidContext()))
                    .build()
            }

            single {
                Retrofit.Builder()
                    .baseUrl("https://hiring.revolut.codes/api/android/")
                    .client(get())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                    .build()
            }

            single<ApiManager> { ApiManagerImpl(get()) }
        }

    val repositoryModule = module {
        factory<RatesConverterRepository> {
            RatesConverterRepositoryImpl(get())
        }
    }

    val viewModelModule = module {
        viewModel {
            RatesConverterViewModel(get())
        }
    }

}
