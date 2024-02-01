package com.example.testshopapplication.support.extensions

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

fun Fragment.hideKeyboard() {
    view?.let { activity?.hideKeyboard(it) }
}
fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}

fun Fragment.popBackStack() {
    activity?.let {
        findNavController().popBackStack()
    }
}

fun Fragment.goto(id: Int) {
    activity?.let {
        findNavController().navigate(id)
    }
}


fun Fragment.goto(id: Int, bundle: Bundle) {
    activity?.let {
        findNavController().navigate(id, bundle)
    }
}