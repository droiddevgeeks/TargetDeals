package com.target.targetcasestudy.ui.deals

import android.graphics.Paint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.target.targetcasestudy.R
import com.target.targetcasestudy.data.DealItem
import com.target.targetcasestudy.databinding.FragmentDealItemBinding
import com.target.targetcasestudy.extensions.gone
import com.target.targetcasestudy.extensions.setImageUrl
import com.target.targetcasestudy.extensions.visible

class DealItemFragment : AbstractDealItemFragment() {

    private lateinit var binding: FragmentDealItemBinding

    companion object {
        fun getInstance(dealId: String): DealItemFragment {
            val arg = Bundle().apply { putString(DEAL_ID, dealId) }
            return DealItemFragment().apply { arguments = arg }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDealItemBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun setDealDetailsData(deal: DealItem) {
        with(binding) {
            dealItemImage.setImageUrl(deal.productImage)
            dealItemTitle.text = deal.title
            dealItemDescription.text = deal.description
            dealRegPrice.text =
                "${context?.getString(R.string.regular_price_text, deal.regularPrice.price)}"
            dealRegPrice.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            dealSpecialPrice.text = deal.salePrice?.price ?: deal.regularPrice.price
        }
    }

    override fun showLoadingState(loading: Boolean) {
        with(binding.itemLoading) {
            if (loading) {
                shimmerViewContainer.visible()
                shimmerViewContainer.startShimmer()
            } else {
                shimmerViewContainer.stopShimmer()
                shimmerViewContainer.gone()
            }
        }
    }

    override fun onError(message: String) {
        showToast(message)
        parentFragmentManager.popBackStack()
    }
}
