package com.target.targetcasestudy.ui.deals

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.target.targetcasestudy.common.BaseFragment
import com.target.targetcasestudy.common.EventObserver
import com.target.targetcasestudy.data.DealItem
import com.target.targetcasestudy.data.DealsState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
abstract class AbstractDealItemFragment : BaseFragment() {

    companion object {
        const val DEAL_ID = "Deal_Id"
    }

    private val viewModel: DealsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getDealId()?.let { dealId -> viewModel.fetchDealDetails(dealId) }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeDataChange()
    }

    private fun getDealId() = arguments?.getString(DEAL_ID)

    private fun observeDataChange() {
        viewModel.dealDetailLiveData.observe(viewLifecycleOwner, EventObserver { state ->
            when (state) {
                is DealsState.Loading -> showLoadingState(state.isLoading)
                is DealsState.Error -> handleError(state.error)
                is DealsState.Success -> setDealDetailsData(state.data)
            }
        })
    }


    abstract fun setDealDetailsData(deal: DealItem)
    abstract fun showLoadingState(loading: Boolean)
}