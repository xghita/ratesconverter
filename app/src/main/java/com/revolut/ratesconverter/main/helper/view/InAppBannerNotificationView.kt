package com.cofedistrict.helpers.ui

import android.app.Activity
import android.content.Context
import android.text.TextUtils.isEmpty
import android.view.View
import android.widget.TextView
import com.revolut.ratesconverter.R
import com.shasin.notificationbanner.Banner

object InAppBannerNotificationView {

    fun showErrorNotification(containerView: View, activity: Activity, message: String?) {
        val errorMessage = if (isEmpty(message)) "Error" else message
        Banner.make(containerView, activity, Banner.TOP, R.layout.error_inapp_notification)
        Banner.getInstance().bannerView.findViewById<TextView>(R.id.notificationMessageTextView).text =
            errorMessage
        Banner.getInstance().setCustomAnimationStyle(R.style.NotificationAnimationTop)
        Banner.getInstance().duration = 2000
        Banner.getInstance().show()
    }
}