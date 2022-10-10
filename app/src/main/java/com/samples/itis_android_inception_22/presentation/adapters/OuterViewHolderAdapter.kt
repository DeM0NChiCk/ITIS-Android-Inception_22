package com.samples.itis_android_inception_22.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.samples.itis_android_inception_22.R
import com.samples.itis_android_inception_22.presentation.adapters.viewHolders.OuterViewHolderExample
import com.samples.itis_android_inception_22.presentation.models.ItemDataModel

class OuterViewHolderAdapter(
    val itemsList: List<ItemDataModel>,
    private val adapterClickListener: ((ItemDataModel) -> Unit)
) : RecyclerView.Adapter<OuterViewHolderExample>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OuterViewHolderExample {
        return OuterViewHolderExample(
            LayoutInflater.from(parent.context).inflate(R.layout.item_outer_view_holder, parent, false),
            onItemClickListener = adapterClickListener
        )
    }

    override fun onBindViewHolder(holder: OuterViewHolderExample, position: Int) {
        holder.bindItemFromOuterViewHolder(itemsList[position])
    }

    override fun getItemCount(): Int = itemsList.size
}