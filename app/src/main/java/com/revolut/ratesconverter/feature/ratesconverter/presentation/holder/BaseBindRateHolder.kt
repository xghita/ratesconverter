package com.revolut.ratesconverter.feature.ratesconverter.presentation.holder

import android.graphics.drawable.GradientDrawable
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.revolut.ratesconverter.main.data.model.currency.Currency
import kotlinx.android.synthetic.main.item_rate_converter.view.*

open class BaseBindRateHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {

    open fun bindCurrency(currency: Currency) {
        val context = itemView.context

        itemView.currencyCodeTv.text = currency.code
        itemView.currencyNameTv.text = currency.code.toLowerCase().capitalize()
        itemView.currencyImageTv.text = currency.code.first().toString()

        val background = itemView.currencyImageTv.background as GradientDrawable
        background.setColor(
            context.getColor(
                context.resources.getIdentifier(
                    currency.code,
                    "color",
                    context.packageName
                )
            )
        )

    }
}