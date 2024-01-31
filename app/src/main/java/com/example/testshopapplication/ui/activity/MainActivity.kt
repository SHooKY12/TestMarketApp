package com.example.testshopapplication.ui.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.testshopapplication.R
import com.example.testshopapplication.databinding.MainActivityBinding
import com.example.testshopapplication.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private var navController: NavController? = null
    private lateinit var binding: MainActivityBinding
    override val viewModel: MainViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initNavigation()
    }

    private fun initNavigation() {
        if (navController == null) {
            navController =
                (supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment).navController
            navController?.navInflater?.inflate(R.navigation.nav_graph)?.apply {
                if (viewModel.loginState()) {
                    setStartDestination(R.id.homeFragment)
                } else {
                    setStartDestination(R.id.signInFragment)
                }
                navController?.setGraph(this, intent.extras)
            }
            navController?.addOnDestinationChangedListener { _, destination, _ ->
                if (destination.id == R.id.signInFragment) {
                    makeInvisibleNavigation()
                } else {
                    makeVisibleNavigation()
                }
            }
        }
    }

}
