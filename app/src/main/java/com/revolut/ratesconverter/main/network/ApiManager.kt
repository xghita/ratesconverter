package com.revolut.ratesconverter.main.network

import com.revolut.ratesconverter.main.data.model.currency.RatesConverterServerResponse
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiManager {

    @GET("latest?")
    fun getCurrenciesList(@Query("base") baseCurrencyCode: String): Observable<RatesConverterServerResponse>

}