package com.example.testshopapplication.ui.custom

import android.content.Context
import android.util.AttributeSet
import com.example.testshopapplication.R
import com.example.testshopapplication.ui.custom.text_watchers.AmountTextWatcher
import com.example.testshopapplication.ui.custom.text_watchers.MaskTextWatcher
import com.google.android.material.textfield.TextInputEditText

class MaskEditText : TextInputEditText {

    private var maskTextWatcher: MaskTextWatcher? = null
    private var amountTextWatcher: AmountTextWatcher? = null

    var mask: String? = null
        set(value) {
            field = value
            if (value.isNullOrEmpty()) {
                removeTextChangedListener(maskTextWatcher)
            } else {
                if (value == "###") {
                    amountTextWatcher = AmountTextWatcher(this)
                    addTextChangedListener(amountTextWatcher)
                } else {
                    maskTextWatcher = MaskTextWatcher(this, mask!!)
                    addTextChangedListener(maskTextWatcher)
                }
            }
        }

    val rawText: String
        get() {
            val formatted = text
            return maskTextWatcher?.unformat(formatted) ?: formatted.toString()
        }

    val cleanText: String?
        get() = text?.toString()?.replace(" ", "")

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(context, attrs)
    }

    private fun init(context: Context, attrs: AttributeSet?) {
        attrs?.let {
            val a = context.obtainStyledAttributes(it, R.styleable.MaskEditText)
            with(a) {
                mask = getString(R.styleable.MaskEditText_mask_edit)
                recycle()
            }
        }
    }
}