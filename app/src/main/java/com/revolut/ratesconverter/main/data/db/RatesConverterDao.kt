package com.revolut.ratesconverter.main.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.revolut.ratesconverter.main.data.model.currency.Currency
import com.revolut.ratesconverter.main.data.model.currency.CurrencyDto

@Dao
interface RatesConverterDao {

    @Query("SELECT * from currency_table")
    fun getCurrenciesList(): LiveData<List<CurrencyDto>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateCurrenciesList(currencies: List<CurrencyDto>)
}