package com.revolut.ratesconverter.main.network

import com.revolut.ratesconverter.main.data.model.currency.RatesConverterServerResponse
import io.reactivex.rxjava3.core.Observable
import retrofit2.Retrofit

class ApiManagerImpl(retrofit: Retrofit) : ApiManager {

    private val apiCall: ApiManager = retrofit.create(ApiManager::class.java)

    override fun getCurrenciesList(baseCurrencyCode: String): Observable<RatesConverterServerResponse> {
        return apiCall.getCurrenciesList(baseCurrencyCode)
    }

}

