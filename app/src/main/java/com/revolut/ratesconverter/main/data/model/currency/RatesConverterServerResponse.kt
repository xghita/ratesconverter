package com.revolut.ratesconverter.main.data.model.currency

import androidx.annotation.Keep

@Keep
data class RatesConverterServerResponse(
    val baseCurrency: String,
    val rates: HashMap<String, String>
)