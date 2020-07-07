package com.revolut.ratesconverter.main.data.model.currency

import androidx.annotation.Keep

@Keep
data class Currency(var code: String, var rate: Double)