package com.revolut.ratesconverter.feature.ratesconverter.domain

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.revolut.ratesconverter.feature.ratesconverter.data.RatesConverterRepository
import com.revolut.ratesconverter.feature.ratesconverter.data.RatesMapper
import com.revolut.ratesconverter.main.data.model.currency.Currency
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class RatesConverterViewModel(private val ratesConverterRepository: RatesConverterRepository) :
    ViewModel(), RateChangedListener {

    val currenciesList = MutableLiveData<List<Currency>>()

    val convertedValue = MutableLiveData<Double>()

    var baseCurrencyCode = MutableLiveData<String>()

    var serverError = MutableLiveData<String>()

    var previousBaseCurrencyPosition: Int = 0
    private lateinit var dispose: Disposable
    private val recursiveAction: Observable<Long> = Observable.interval(0, 1, TimeUnit.MINUTES)

    fun updateCurrenciesList() {

        dispose = recursiveAction
            .doOnNext {

                ratesConverterRepository.getCurrenciesList(baseCurrencyCode.value ?: "EUR")
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({

                        currenciesList.value =
                            RatesMapper.createCurrenciesList(
                                it.rates,
                                baseCurrencyCode.value ?: "EUR",
                                convertedValue.value ?: 1.0
                            )
                    }, {
                        serverError.value = it.localizedMessage
                    })
            }
            .subscribe()
    }

    fun onStop() {
        disposeObservable()
    }

    override fun onBaseCurrencyChanged(
        newBaseCurrency: Currency,
        position: Int,
        resetBaseRate: Boolean
    ) {
        onConvertedValueChanged(newBaseCurrency.rate * (convertedValue.value ?: 1.0))

        previousBaseCurrencyPosition = position

        this.baseCurrencyCode.value = newBaseCurrency.code

        disposeObservable()
        updateCurrenciesList()
    }

    override fun onConvertedValueChanged(
        convertedValue: Double,
        resetBaseRate: Boolean
    ) {
        this.convertedValue.value = convertedValue
    }

    private fun disposeObservable() {
        if (::dispose.isInitialized && !dispose.isDisposed) {
            dispose.dispose()
        }
    }

}