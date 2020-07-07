package com.revolut.ratesconverter.main.helper.view

import android.content.Context
import android.text.TextWatcher
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText

class EditTextWithRemovableTextWatchers(context: Context?, attrs: AttributeSet?) : AppCompatEditText(context, attrs) {

    private val listeners by lazy { mutableListOf<TextWatcher>() }

    override fun addTextChangedListener(watcher: TextWatcher) {
        listeners.add(watcher)
        super.addTextChangedListener(watcher)
    }

    override fun removeTextChangedListener(watcher: TextWatcher) {
        listeners.remove(watcher)
        super.removeTextChangedListener(watcher)
    }

    fun clearTextChangedListeners() {
        for (watcher in listeners) super.removeTextChangedListener(watcher)
        listeners.clear()
    }
}