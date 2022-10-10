package com.samples.itis_android_inception_22.presentation.adapters.viewHolders

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.samples.itis_android_inception_22.R
import com.samples.itis_android_inception_22.presentation.models.ItemDataModel

class OuterViewHolderExample(
    rvItemView: View,
    private val onItemClickListener: (ItemDataModel) -> Unit
) : RecyclerView.ViewHolder(rvItemView) {

    private val headerTv: TextView
    private val dividerView: View
    // private var dataModel: ItemDataModel? = null

    init {
        headerTv = rvItemView.findViewById(R.id.item_header_tv)
        dividerView = rvItemView.findViewById(R.id.divider_view)
    }

    fun bindItemFromOuterViewHolder(itemModel: ItemDataModel) {
        headerTv.text = itemModel.headerText
        itemView.setOnClickListener {
            onItemClickListener(itemModel)
        }
    }
}

/* Второй вариант того, как можно объявить onItemClickListener
 * Но это не очень надежный вариант
class OuterViewHolderExample(
    rvItemView: View,
    private val onItemClickListener: (ItemDataModel) -> Unit
) : RecyclerView.ViewHolder(rvItemView) {

    private val headerTv: TextView
    private val dividerView: View
    private var dataModel: ItemDataModel? = null

    init {
        headerTv = rvItemView.findViewById(R.id.item_header_tv)
        dividerView = rvItemView.findViewById(R.id.divider_view)

        rvItemView.setOnClickListener {
            dataModel?.also(onItemClickListener)
        }
    }

    fun bindItemFromOuterViewHolder(itemModel: ItemDataModel) {
        if (dataModel == null) {
            dataModel = itemModel
        }
        headerTv.text = itemModel.headerText
    }
}
 */