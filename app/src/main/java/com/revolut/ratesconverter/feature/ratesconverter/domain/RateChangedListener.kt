package com.revolut.ratesconverter.feature.ratesconverter.domain

import com.revolut.ratesconverter.main.data.model.currency.Currency


interface RateChangedListener {

    fun onConvertedValueChanged(convertedValue: Double, resetBaseRate:Boolean = false)
    fun onBaseCurrencyChanged(newBaseCurrency: Currency, position: Int, resetBaseRate:Boolean = true)

}