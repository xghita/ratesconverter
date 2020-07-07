package com.revolut.ratesconverter.main.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.revolut.ratesconverter.main.data.model.currency.Currency
import com.revolut.ratesconverter.main.data.model.currency.CurrencyDto

@Database(entities = [CurrencyDto::class], version = 1, exportSchema = false)
abstract class RatesConverterDatabase : RoomDatabase() {

    abstract fun ratesConverterDao(): RatesConverterDao

    companion object {
        const val DATABASE_NAME = "app_db"
    }
}