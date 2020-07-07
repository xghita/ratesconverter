package com.revolut.ratesconverter.feature.ratesconverter.presentation.holder

import android.view.View
import com.revolut.ratesconverter.feature.ratesconverter.domain.RateChangedListener
import com.revolut.ratesconverter.main.data.model.currency.Currency
import com.revolut.ratesconverter.main.helper.extensions.afterTextChanged
import com.revolut.ratesconverter.main.helper.extensions.roundOffDecimal
import kotlinx.android.synthetic.main.item_rate_converter.view.*

class SelectedBaseRateHolder(itemView: View?) : BaseBindRateHolder(itemView!!) {

    fun bindView(
        currency: Currency,
        userActionListener: RateChangedListener
    ) {
        if (currency.code != itemView.currencyCodeTv.text.toString()) {

            itemView.currencyConvertedValueEt.clearTextChangedListeners()

            val rateValue = if (currency.rate % 1 == 0.0) {
                (currency.rate).toInt().toString()
            } else {
                (currency.rate).roundOffDecimal().toString()
            }

            itemView.currencyConvertedValueEt.setText(rateValue)

            itemView.currencyConvertedValueEt.setSelection(
                itemView.currencyConvertedValueEt.text.toString().length
            )
        }

        super.bindCurrency(currency)

        itemView.currencyConvertedValueEt.isEnabled = true

        itemView.currencyConvertedValueEt.afterTextChanged {

            when (it) {
                "" -> {
                    userActionListener.onConvertedValueChanged(0.0)
                }
                else -> {
                    userActionListener.onConvertedValueChanged(it.toDouble())
                }
            }

        }
    }

}