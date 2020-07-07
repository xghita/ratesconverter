package com.revolut.ratesconverter.main.data.model.currency

import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey

@Keep
@Entity(tableName = "currency_table")
data class CurrencyDto(@PrimaryKey var code: String, var name: String, var rateBase: Double)