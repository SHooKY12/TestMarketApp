package com.example.testshopapplication.ui.custom.text_watchers

import android.text.Editable
import android.text.TextWatcher
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import java.text.DecimalFormat

class AmountTextWatcher internal constructor(
    private val editText: TextInputEditText
) : TextWatcher {
    private var previousCleanString: String? = null

    private fun noSpaceNoComma(text: String): String {
        var newText = text
        if (text.contains(" ")) {
            newText = text.replace(" ", "")
        }
        if (text.contains(",")) {
            newText = text.replace(",", ".")
        }
        return newText
    }

    private fun putMoneySpace(dataToSpace: String): String {
        val str = StringBuilder()
        var counter = -4
        var dot = false
        if (dataToSpace.contains(".")) {
            counter = -3
            dot = true
        }
        for (k in dataToSpace.length - 1 downTo 0) {
            if (counter == 3) {
                str.insert(0, dataToSpace[k] + " ")
                if (dot) {
                    counter = 1
                } else {
                    counter = 0
                }
            } else {
                str.insert(0, dataToSpace[k])
                counter++
            }
        }
        return str.toString()
    }

    override fun afterTextChanged(editable: Editable?) {
        val str = editable.toString()
        val cleanString = noSpaceNoComma(str)
        if (cleanString == previousCleanString || cleanString.isEmpty()) {
            return
        }
        previousCleanString = cleanString

        val formattedString: String = if (cleanString.contains(".")) {
            putMoneySpace(cleanString)
        } else {
            toString(cleanString.toLong())
        }
        editable!!.replace(0, editable.length, formattedString)
    }

    fun toString(i: Long): String {
        val nf = DecimalFormat("###,##0")
        val dfs = nf.decimalFormatSymbols
        dfs.groupingSeparator = ' '
        nf.decimalFormatSymbols = dfs
        return nf.format(i)
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        //
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        if (editText.parent is TextInputLayout) {
            (editText.parent as TextInputLayout).error = null
            (editText.parent as TextInputLayout).isErrorEnabled = false
        }
    }

    companion object {
        val MAX_DECIMAL = 2
        val MAX_LENGTH = 16
    }
}