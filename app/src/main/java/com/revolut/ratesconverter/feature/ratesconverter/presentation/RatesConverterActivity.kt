package com.revolut.ratesconverter.feature.ratesconverter.presentation

import android.os.Bundle
import android.os.Handler
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.SimpleItemAnimator
import com.revolut.ratesconverter.R
import com.revolut.ratesconverter.base.presentation.BaseActivity
import com.revolut.ratesconverter.feature.ratesconverter.domain.RatesConverterViewModel
import com.revolut.ratesconverter.main.helper.view.InAppBannerNotificationView
import kotlinx.android.synthetic.main.activity_rates_converter.*
import org.koin.androidx.viewmodel.ext.android.getViewModel


class RatesConverterActivity : BaseActivity(R.layout.activity_rates_converter) {

    lateinit var ratesConverterViewModel: RatesConverterViewModel
    private lateinit var ratesAdapter: RatesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ratesAdapter = RatesAdapter(userActionListener = ratesConverterViewModel)

        with(ratesRv) {
            adapter = ratesAdapter
            layoutManager = LinearLayoutManager(context)
            (itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false
        }
    }

    override fun onStart() {
        super.onStart()
        ratesConverterViewModel.updateCurrenciesList()
    }

    override fun onStop() {
        ratesConverterViewModel.onStop()
        super.onStop()
    }

    override fun setupViewModel() {
        ratesConverterViewModel = getViewModel()
    }

    override fun bindViewModelObservers() {
        ratesConverterViewModel.currenciesList.observe(this, Observer {

            if (ratesRv.isComputingLayout) {
                Handler().post {
                    ratesAdapter.updateCurrenciesList(it)
                }
            } else {
                ratesAdapter.updateCurrenciesList(it)
            }
        })

        ratesConverterViewModel.convertedValue.observe(this, Observer {

            if (ratesRv.isComputingLayout) {
                Handler().post {
                    ratesAdapter.updateBaseRateValue(it)
                }
            } else {
                ratesAdapter.updateBaseRateValue(it)
            }
        })

        ratesConverterViewModel.baseCurrencyCode.observe(this, Observer {

            if (ratesRv.isComputingLayout) {
                Handler().post {
                    ratesAdapter.moveItem(ratesConverterViewModel.previousBaseCurrencyPosition, 0)
                }
            } else {
                ratesAdapter.moveItem(ratesConverterViewModel.previousBaseCurrencyPosition, 0)
            }
        })

        ratesConverterViewModel.serverError.observe(this, Observer {
            InAppBannerNotificationView.showErrorNotification(ratesLayoutContainer, this, it)
        })
    }

}