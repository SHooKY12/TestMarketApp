package com.example.testshopapplication.ui.fragments

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.example.testshopapplication.R
import com.example.testshopapplication.shared.enums.MessageType
import com.example.testshopapplication.support.extensions.hideKeyboard
import com.example.testshopapplication.ui.base.BaseViewModel
import com.google.android.material.snackbar.Snackbar

typealias Inflate<T> = (LayoutInflater, ViewGroup?, Boolean) -> T

abstract class BaseFragment<VB : ViewBinding>(
    private val inflate: Inflate<VB>
) : Fragment() {

    private var _binding: VB? = null
    val binding get() = _binding!!
    open val viewModel: BaseViewModel? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = inflate.invoke(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    protected fun showMessage(message: Int, messageType: MessageType = MessageType.ERROR) {
        view?.let {
            hideKeyboard()
            Snackbar.make(it, message, Snackbar.LENGTH_LONG).apply {
                setTextColor(Color.WHITE)
                val snackBarView = this.view
                val color = when (messageType) {
                    MessageType.WARNING -> {
                        setTextColor(Color.BLACK)
                        Color.YELLOW
                    }

                    MessageType.ERROR -> Color.RED
                    else -> {
                        ContextCompat.getColor(it.context, R.color.color_green)
                    }
                }
                snackBarView.setBackgroundColor(color)
            }.show()
        }
    }
}