package com.target.targetcasestudy.ui.deals

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.target.targetcasestudy.data.DealItem
import com.target.targetcasestudy.databinding.FragmentDealListBinding
import com.target.targetcasestudy.extensions.gone
import com.target.targetcasestudy.extensions.visible
import com.target.targetcasestudy.ui.callback.IFragmentChangeCallback

class DealListFragment : AbstractDealListFragment() {

    companion object {
        fun newInstance(): DealListFragment {
            return DealListFragment()
        }
    }

    private lateinit var fragmentChangeCallback: IFragmentChangeCallback
    private lateinit var binding: FragmentDealListBinding

    private val dealsList by lazy { ArrayList<DealItem>() }
    private val dealItemAdapter by lazy {
        DealItemAdapter(dealsList) { dealId ->
            onDealItemClick(dealId)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        fragmentChangeCallback = context as IFragmentChangeCallback
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        binding = FragmentDealListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeDataChange()
        initAdapter()
    }

    private fun initAdapter() {
        with(binding.dealRecyclerView) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = dealItemAdapter
        }
    }

    private fun onDealItemClick(dealId: String) {
        val fragment = DealItemFragment.getInstance(dealId)
        fragmentChangeCallback.onFragmentChange(fragment)
    }

    override fun setAllDeals(list: List<DealItem>) {
        dealsList.clear()
        dealsList.addAll(list)
        with(dealItemAdapter) {
            notifyDataSetChanged()
        }
    }

    override fun showLoadingState(loading: Boolean) {
        with(binding.shimmerLoading) {
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
    }
}
