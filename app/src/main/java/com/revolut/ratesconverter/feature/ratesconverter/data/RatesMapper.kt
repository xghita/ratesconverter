package com.revolut.ratesconverter.feature.ratesconverter.data

import com.revolut.ratesconverter.main.data.model.currency.Currency

object RatesMapper {

    fun mapHashMapToList(
        rates: HashMap<String, String>,
        baseCurrencyCode: String,
        convertedValue: Double
    ): ArrayList<Currency> {

        val currenciesListMapper = ArrayList<Currency>()

        currenciesListMapper.add(
            Currency(
                code = baseCurrencyCode,
                rate = convertedValue
            )
        )

        for (currency in rates) {
            currenciesListMapper.add(
                Currency(
                    code = currency.key,
                    rate = currency.value.toDouble()
                )
            )
        }

        return currenciesListMapper
    }
}