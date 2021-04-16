package com.target.targetcasestudy.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.target.targetcasestudy.R
import com.target.targetcasestudy.databinding.ActivityMainBinding
import com.target.targetcasestudy.extensions.replaceFragment
import com.target.targetcasestudy.ui.callback.IFragmentChangeCallback
import com.target.targetcasestudy.ui.deals.DealListFragment
import com.target.targetcasestudy.ui.payment.PaymentDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), IFragmentChangeCallback {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(DealListFragment.newInstance(), R.id.container, false)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.credit_card -> {
                PaymentDialogFragment().show(supportFragmentManager, "CreditCardValidation")
                true
            }
            else -> false
        }
    }

    override fun onFragmentChange(fragment: Fragment) {
        replaceFragment(fragment, R.id.container, true)
    }
}
