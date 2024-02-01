package com.example.testshopapplication.ui.fragments.sign_in_fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import com.example.testshopapplication.R
import com.example.testshopapplication.databinding.FragmentSignInFragmentBinding
import com.example.testshopapplication.ui.fragments.BaseFragment

class SignInFragment :
    BaseFragment<FragmentSignInFragmentBinding>(FragmentSignInFragmentBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun initViews() = with(binding) {

        etName.setOnTouchListener(View.OnTouchListener { _, event ->
            val drawableRight = 2
            if (event.action == MotionEvent.ACTION_UP) {
                etName.compoundDrawables[drawableRight]?.let {
                    if (event.rawX >= etName.right - it.bounds.width()) {
                        etName.setText("")
                        return@OnTouchListener true
                    }
                }
            }
            false
        })
        etName.addTextChangedListener { p0 ->
            if (p0?.isNotEmpty() == true) {
                binding.etName.setCompoundDrawablesWithIntrinsicBounds(
                    0, 0, R.drawable.et_clear, 0
                )
            } else {
                etName.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0)
            }
        }
        tvTerms.paint?.isUnderlineText = true
    }
}