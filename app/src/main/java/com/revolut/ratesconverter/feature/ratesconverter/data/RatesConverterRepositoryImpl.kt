package com.revolut.ratesconverter.feature.ratesconverter.data

import com.revolut.ratesconverter.main.data.db.RatesConverterDao
import com.revolut.ratesconverter.main.data.model.currency.Currency
import com.revolut.ratesconverter.main.data.model.currency.CurrencyDto
import com.revolut.ratesconverter.main.data.model.currency.RatesConverterServerResponse
import com.revolut.ratesconverter.main.network.ApiManager
import io.reactivex.rxjava3.core.Observable

class RatesConverterRepositoryImpl(
    private val apiManager: ApiManager,
    private val ratesConverterDao: RatesConverterDao
) : RatesConverterRepository {


    override fun getCurrenciesList(baseCurrencyCode: String): Observable<RatesConverterServerResponse> {
        return apiManager.getCurrenciesList(baseCurrencyCode)
    }

}