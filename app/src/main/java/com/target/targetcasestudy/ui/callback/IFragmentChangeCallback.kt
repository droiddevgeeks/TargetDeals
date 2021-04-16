package com.target.targetcasestudy.ui.callback

import androidx.fragment.app.Fragment

/**
 * This interface is implemented by any activity.
 * This is used to handle fragment transaction based on user action
 */
interface IFragmentChangeCallback {
    fun onFragmentChange(fragment: Fragment)
}