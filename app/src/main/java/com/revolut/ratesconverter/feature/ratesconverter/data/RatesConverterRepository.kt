package com.revolut.ratesconverter.feature.ratesconverter.data

import com.revolut.ratesconverter.main.data.model.currency.Currency
import com.revolut.ratesconverter.main.data.model.currency.CurrencyDto
import com.revolut.ratesconverter.main.data.model.currency.RatesConverterServerResponse
import io.reactivex.rxjava3.core.Observable


interface RatesConverterRepository {

    /**
     * get currencies list from remote
     */
    fun getCurrenciesList(baseCurrencyCode: String): Observable<RatesConverterServerResponse>

}