package com.example.testshopapplication.ui.fragments.sign_in_fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.TextWatcher
import android.view.MotionEvent
import android.view.View
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import com.example.testshopapplication.R
import com.example.testshopapplication.databinding.FragmentSignInFragmentBinding
import com.example.testshopapplication.ui.fragments.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignInFragment :
    BaseFragment<FragmentSignInFragmentBinding>(FragmentSignInFragmentBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() = with(binding) {

        etName.textChangedListener()
        etSurname.textChangedListener()
        tvTerms.paint?.isUnderlineText = true
    }



    @SuppressLint("ClickableViewAccessibility")
    private fun EditText.textChangedListener() {
        setOnTouchListener(View.OnTouchListener { _, event ->
            val drawableRight = 2
            if (event.action == MotionEvent.ACTION_UP) {
                this.compoundDrawables[drawableRight]?.let {
                    if (event.rawX >= this.right - it.bounds.width()) {
                        this.setText("")
                        return@OnTouchListener true
                    }
                }
            }
            false
        })
        addTextChangedListener { p0 ->
            if (p0?.isNotEmpty() == true) {
                binding.etName.setCompoundDrawablesWithIntrinsicBounds(
                    0, 0, R.drawable.et_clear, 0
                )
            } else {
                this.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0)
            }
        }
    }
}