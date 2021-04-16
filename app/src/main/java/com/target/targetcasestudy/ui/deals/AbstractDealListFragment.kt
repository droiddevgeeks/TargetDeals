package com.target.targetcasestudy.ui.deals

import android.os.Bundle
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.target.targetcasestudy.common.BaseFragment
import com.target.targetcasestudy.common.EventObserver
import com.target.targetcasestudy.data.DealItem
import com.target.targetcasestudy.data.DealsState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
abstract class AbstractDealListFragment : BaseFragment() {

    private val viewModel: DealsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.fetchAllDeals()
    }

    protected fun observeDataChange() {
        viewModel.dealsLiveData.observe(viewLifecycleOwner, EventObserver { state ->
            when (state) {
                is DealsState.Loading -> showLoadingState(state.isLoading)
                is DealsState.Error -> handleError(state.error)
                is DealsState.Success -> setAllDeals(state.data.products)
            }
        })
    }

    abstract fun setAllDeals(list: List<DealItem>)
    abstract fun showLoadingState(loading: Boolean)
}
