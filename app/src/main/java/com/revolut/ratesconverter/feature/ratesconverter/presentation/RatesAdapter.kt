package com.revolut.ratesconverter.feature.ratesconverter.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.revolut.ratesconverter.R
import com.revolut.ratesconverter.feature.ratesconverter.domain.RateChangedListener
import com.revolut.ratesconverter.feature.ratesconverter.domain.RatesDiffCallback
import com.revolut.ratesconverter.feature.ratesconverter.presentation.holder.SelectedBaseRateHolder
import com.revolut.ratesconverter.feature.ratesconverter.presentation.holder.ListRateHolder
import com.revolut.ratesconverter.feature.ratesconverter.presentation.holder.UnknownErrorHolder
import com.revolut.ratesconverter.main.data.model.currency.Currency
import com.revolut.ratesconverter.main.helper.Enums

class RatesAdapter(
    private var currenciesList: MutableList<Currency> = ArrayList(),
    private var convertedValue: Double = 1.0,
    private val userActionListener: RateChangedListener
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return when (viewType) {
            Enums.RATE_CONVERTER_TYPE.SELECTED_TOP_RATE.ordinal -> SelectedBaseRateHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_rate_converter, parent, false)
            )
            Enums.RATE_CONVERTER_TYPE.LIST_RATE.ordinal -> ListRateHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_rate_converter, parent, false)
            )
            else -> {
                UnknownErrorHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_rate_error, parent, false)
                )
            }
        }
    }

    override fun getItemCount(): Int {
        return currenciesList.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) {
            Enums.RATE_CONVERTER_TYPE.SELECTED_TOP_RATE.ordinal
        } else {
            Enums.RATE_CONVERTER_TYPE.LIST_RATE.ordinal
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when (holder.itemViewType) {
            Enums.RATE_CONVERTER_TYPE.SELECTED_TOP_RATE.ordinal -> {
                val viewHolder = holder as SelectedBaseRateHolder
                viewHolder.bindView(currenciesList[position], userActionListener)
            }
            Enums.RATE_CONVERTER_TYPE.LIST_RATE.ordinal -> {
                val viewHolder = holder as ListRateHolder
                viewHolder.bindView(
                    currenciesList[position],
                    convertedValue,
                    userActionListener,
                    position
                )
            }
            else -> {
                val viewHolder = holder as UnknownErrorHolder
                viewHolder.bindView()
            }
        }
    }

    fun updateCurrenciesList(newCurrenciesList: List<Currency>) {
        val diffResult =
            DiffUtil.calculateDiff(
                RatesDiffCallback(currenciesList, newCurrenciesList)
            )

        currenciesList.clear()
        currenciesList.addAll(newCurrenciesList)

        diffResult.dispatchUpdatesTo(this)
    }

    fun updateBaseRateValue(convertedValue: Double) {
        this.convertedValue = convertedValue
        this.notifyDataSetChanged()
    }

    fun moveItem(fromPosition: Int, toPosition: Int) {
        if (fromPosition == toPosition) return

        val movingItem = currenciesList.removeAt(fromPosition)
        if (fromPosition < toPosition) {
            currenciesList.add(toPosition - 1, movingItem)
        } else {
            currenciesList.add(toPosition, movingItem)
        }

        notifyItemMoved(fromPosition, toPosition)
    }

}