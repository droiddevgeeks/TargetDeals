package com.target.targetcasestudy.ui.deals

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.target.targetcasestudy.R
import com.target.targetcasestudy.data.DealItem
import com.target.targetcasestudy.databinding.DealListItemBinding
import com.target.targetcasestudy.extensions.setImageUrl

/**
 * This is Deal List Item Adapter class which takes list & click callback as its parameter.
 * @param dealsList : List of items
 * @param itemClick callback for item click to Fragment.
 */
class DealItemAdapter constructor(
    private val dealsList: List<DealItem>,
    private val itemClick: (String) -> Unit
) :
    RecyclerView.Adapter<DealItemAdapter.DealItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DealItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DealListItemBinding.inflate(inflater, parent, false)
        return DealItemViewHolder(binding)
    }

    override fun getItemCount(): Int = dealsList.size

    override fun onBindViewHolder(viewHolder: DealItemViewHolder, position: Int) {
        val item = dealsList[position]
        with(viewHolder.binding) {
            dealItemTitle.text = item.title
            dealItemPrice.text = item.regularPrice.price
            dealItemAisle.text = item.aisle.toUpperCase()
            dealItemImage.setImageUrl(item.productImage)
            root.setOnClickListener { itemClick("${item.id}") }
        }
    }

    inner class DealItemViewHolder(val binding: DealListItemBinding) :
        RecyclerView.ViewHolder(binding.root)
}
