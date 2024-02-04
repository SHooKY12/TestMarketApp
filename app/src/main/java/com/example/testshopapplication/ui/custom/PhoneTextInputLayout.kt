package com.example.testshopapplication.ui.custom

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.example.testshopapplication.databinding.ViewPhoneTextInputBinding

class PhoneTextInputLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : FrameLayout(context, attrs, defStyleAttr) {

    var bindingView: ViewPhoneTextInputBinding? = null

    val phoneNumber: String?
        get() = bindingView?.editText?.cleanText

    init {
        bindingView = ViewPhoneTextInputBinding.inflate(LayoutInflater.from(context), this, true)
    }

    fun setText(text: String?) {
        bindingView?.editText?.setText(text)
    }

    inline fun addTextChangeListener(
        crossinline beforeTextChanged: (
            text: CharSequence?, start: Int, count: Int, after: Int
        ) -> Unit = { _, _, _, _ -> }, crossinline onTextChanged: (
            text: CharSequence?, start: Int, before: Int, count: Int
        ) -> Unit = { _, _, _, _ -> }, crossinline afterTextChanged: (text: Editable?) -> Unit = {}
    ) {
        bindingView?.editText?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(text: CharSequence?, start: Int, count: Int, after: Int) {
                beforeTextChanged.invoke(text, start, count, after)
            }

            override fun onTextChanged(text: CharSequence?, start: Int, before: Int, count: Int) {
                onTextChanged.invoke(text, start, before, count)
            }

            override fun afterTextChanged(s: Editable?) {
                afterTextChanged.invoke(s)
            }

        })
    }

}