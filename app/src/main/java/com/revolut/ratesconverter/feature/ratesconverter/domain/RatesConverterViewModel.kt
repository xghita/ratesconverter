package com.revolut.ratesconverter.feature.ratesconverter.domain

import androidx.lifecycle.MutableLiveData
import com.revolut.ratesconverter.base.domain.BaseViewModel
import com.revolut.ratesconverter.feature.ratesconverter.data.RatesConverterRepository
import com.revolut.ratesconverter.feature.ratesconverter.data.RatesMapper
import com.revolut.ratesconverter.main.data.model.currency.Currency
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class RatesConverterViewModel(private val ratesConverterRepository: RatesConverterRepository) :
    BaseViewModel(), RateChangedListener {

    val currenciesList = MutableLiveData<List<Currency>>()

    val convertedValue = MutableLiveData<Double>()

    var baseCurrencyCode = MutableLiveData<String>()

    var serverError = MutableLiveData<String>()

    var previousBaseCurrencyPosition: Int = 0
    private lateinit var dispose: Disposable
    private val recursiveAction: Observable<Long> = Observable.interval(0, 7, TimeUnit.MINUTES)

    fun updateCurrenciesList() {

        dispose = recursiveAction
            .doOnNext {

                ratesConverterRepository.getCurrenciesList(baseCurrencyCode.value ?: "EUR")
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({

                        currenciesList.value =
                            RatesMapper.mapHashMapToList(
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
        position: Int
    ) {
        previousBaseCurrencyPosition = position

        onConvertedValueChanged(newBaseCurrency.rate * (convertedValue.value?:1.0))

        this.baseCurrencyCode.value = newBaseCurrency.code

        disposeObservable()
        updateCurrenciesList()
    }

    override fun onConvertedValueChanged(value: Double) {
        convertedValue.value = value
    }

    private fun disposeObservable() {
        if (::dispose.isInitialized && !dispose.isDisposed) {
            dispose.dispose()
        }
    }

}