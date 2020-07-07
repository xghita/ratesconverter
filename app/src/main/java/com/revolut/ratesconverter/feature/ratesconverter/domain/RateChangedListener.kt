package com.revolut.ratesconverter.feature.ratesconverter.domain

import com.revolut.ratesconverter.main.data.model.currency.Currency


interface RateChangedListener {

    fun onConvertedValueChanged(value: Double)
    fun onBaseCurrencyChanged(newBaseCurrency: Currency, position: Int)

}