package com.revolut.ratesconverter.feature.ratesconverter.domain

import androidx.recyclerview.widget.DiffUtil
import com.revolut.ratesconverter.main.data.model.currency.Currency
import org.koin.ext.getScopeId

class RatesDiffCallback(private val old: List<Currency>, private val new: List<Currency>) :
    DiffUtil.Callback() {
    override fun getOldListSize() = old.size

    override fun getNewListSize() = new.size

    override fun areItemsTheSame(oldIndex: Int, newIndex: Int): Boolean {
        return (old[oldIndex].code == new[newIndex].code)
    }

    override fun areContentsTheSame(oldIndex: Int, newIndex: Int): Boolean {
        return old[oldIndex] == new[newIndex]
    }
}