package com.target.targetcasestudy.extensions

import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.Target.SIZE_ORIGINAL
import com.target.targetcasestudy.R


inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction) {
    beginTransaction().func().commit()
}

/**
 * Extension function to handle fragment transaction
 */
fun AppCompatActivity.replaceFragment(
    fragment: Fragment,
    container: Int,
    addToBackStack: Boolean = false
) {
    supportFragmentManager.inTransaction {
        if (addToBackStack) {
            replace(container, fragment, fragment.javaClass.simpleName)
            addToBackStack(fragment.javaClass.simpleName)
        } else {
            replace(container, fragment, fragment.javaClass.simpleName)
        }
    }
}

/**
 * Extension function to handle image loading using Glide
 */
fun ImageView.setImageUrl(url: String) {
    Glide.with(this.context)
        .load(url)
        .override(SIZE_ORIGINAL)
        .placeholder(R.mipmap.ic_launcher_round)
        .error(R.mipmap.ic_launcher_round)
        .into(this)
}

fun View.gone(){
    this.visibility = View.GONE
}

fun View.visible(){
    this.visibility = View.VISIBLE
}