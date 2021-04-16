package com.target.targetcasestudy.common

import android.widget.Toast
import androidx.fragment.app.Fragment
import com.target.targetcasestudy.R
import okio.IOException
import retrofit2.HttpException


abstract class BaseFragment : Fragment() {

    protected fun handleError(th: Throwable) {
        val error = when (th) {
            is HttpException -> when (th.code()) {
                401 -> getString(R.string.error_server)
                500 -> getString(R.string.error_server)
                else -> getString(R.string.error_server)
            }
            is IOException -> getString(R.string.internet_error)
            else -> getString(R.string.error_server)
        }
        onError(error)
    }

    fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    abstract fun onError(message: String)
}