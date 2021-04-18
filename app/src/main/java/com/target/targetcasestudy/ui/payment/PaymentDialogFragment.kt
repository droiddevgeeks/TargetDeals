package com.target.targetcasestudy.ui.payment

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.animation.doOnEnd
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.DialogFragment
import com.target.targetcasestudy.R
import com.target.targetcasestudy.data.validateCreditCard
import com.target.targetcasestudy.databinding.DialogPaymentBinding

/**
 * Dialog that displays a minimal credit card entry form.
 *
 * Your task here is to enable the "submit" button when the credit card number is valid and
 * disable the button when the credit card number is not valid.
 *
 * You do not need to input any of your actual credit card information. See `Validators.kt` for
 * info to help you get fake credit card numbers.
 *
 * You do not need to make any changes to the layout of this screen (though you are welcome to do
 * so if you wish).
 */
class PaymentDialogFragment : DialogFragment() {

    private lateinit var binding: DialogPaymentBinding
    private lateinit var appearAnimation: ObjectAnimator

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogPaymentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setInputListener()
    }

    override fun onStart() {
        super.onStart()
        handleDialogAppearAnimation()
    }

    private fun handleDialogAppearAnimation() {
        appearAnimation = ObjectAnimator.ofPropertyValuesHolder(
            dialog?.window?.decorView,
            PropertyValuesHolder.ofFloat("scaleX", 0.0f, 1.0f),
            PropertyValuesHolder.ofFloat("scaleY", 0.0f, 1.0f),
            PropertyValuesHolder.ofFloat("alpha", 0.0f, 1.0f)
        ).apply {
            duration = 400L
            start()
        }
    }

    private fun setInputListener() {
        with(binding) {
            cancel.setOnClickListener { handleDialogDisappearAnimation() }
            submit.setOnClickListener {
                Toast.makeText(context, R.string.thanks_message, Toast.LENGTH_SHORT).show()
                handleDialogDisappearAnimation()
            }
            cardNumber.addTextChangedListener {
                submit.isEnabled = validateCreditCard(it.toString())
            }
        }
    }

    private fun handleDialogDisappearAnimation() {
        with(appearAnimation) {
            reverse()
            doOnEnd { dismiss() }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        if (::appearAnimation.isInitialized) {
            appearAnimation.cancel()
            appearAnimation.removeAllUpdateListeners()
        }
    }

}