package com.revolut.ratesconverter.feature.ratesconverter.presentation.holder

import android.util.Log
import android.view.View
import com.revolut.ratesconverter.feature.ratesconverter.domain.RateChangedListener
import com.revolut.ratesconverter.main.data.model.currency.Currency
import com.revolut.ratesconverter.main.helper.extensions.roundOffDecimal
import kotlinx.android.synthetic.main.item_rate_converter.view.*

class ListRateHolder(itemView: View?) : BaseBindRateHolder(itemView!!) {

    fun bindView(
        currency: Currency,
        conversionRate: Double,
        userActionListener: RateChangedListener,
        position: Int
    ) {
        super.bindCurrency(currency)

        itemView.currencyConvertedValueEt.isEnabled = false
        itemView.currencyConvertedValueEt.setText(
            (currency.rate * conversionRate).roundOffDecimal().toString()
        )

        itemView.currencyRateLayout.setOnClickListener {
            userActionListener.onBaseCurrencyChanged(
                currency,
                position
            )
        }
    }

}