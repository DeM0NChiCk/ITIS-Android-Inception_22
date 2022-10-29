package com.samples.itis_android_inception_22.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.samples.itis_android_inception_22.R
import com.samples.itis_android_inception_22.databinding.ItemGridBlockBinding
import com.samples.itis_android_inception_22.presentation.models.GridItemDimensionsModel

class GridManagerAdapter(
    private val gridItems: List<Int>,
    private val dimension: GridItemDimensionsModel,
    private val clickAction: (Int) -> Unit
) :
    RecyclerView.Adapter<GridManagerAdapter.GridLayoutViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GridLayoutViewHolder {
        return GridLayoutViewHolder(
            viewBinding = ItemGridBlockBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: GridLayoutViewHolder, position: Int) {
        /* Для примера, будем делить сетку по блокам из 4 элементов:
         * - Первый и четвертый элемент каждого блока горизонтальные
         * - Второй и третий элемент каждого блока вертикальные
         */
        when (position % 4) {
            0, 3 -> {
                holder.bindHorizontal()
            }
            1, 2 -> {
                holder.bindVertical(position)
            }
        }
    }

    override fun getItemCount(): Int = gridItems.size

    inner class GridLayoutViewHolder(val viewBinding: ItemGridBlockBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {

        init {
            viewBinding.root.setOnClickListener {
                clickAction(bindingAdapterPosition)
            }
        }

        fun bindHorizontal() {
            val halfMargin = dimension.halfMargin
            /* Так как layout manager у нас Staggered Grid Layout manager, то layout params рутового элемента
             * нужно скастить до соответствующего layout params
             * */
            (viewBinding.root.layoutParams as? StaggeredGridLayoutManager.LayoutParams)?.apply {
                width = dimension.horizontalItemWidth
                height = dimension.horizontalItemHeight
                // isFullSpan - Параметр, который отвечает за то, займет ли элемент все доступное место
                // по горизонтали или вертикали, в зависимости от ориентации вашего layout manager-а
                isFullSpan = true
                setMargins(halfMargin * 2, halfMargin, halfMargin * 2, halfMargin)
            }
            viewBinding.root.setCardBackgroundColor(
                ContextCompat.getColor(
                    itemView.context,
                    R.color.blue_200
                )
            )
        }

        fun bindVertical(position: Int) {
            val halfMargin = dimension.halfMargin
            (viewBinding.root.layoutParams as? StaggeredGridLayoutManager.LayoutParams)?.apply {
                width = dimension.verticalItemWidth
                height = dimension.verticalItemHeight
                isFullSpan = false
                when (position % 4) {
                    1 -> setMargins(halfMargin * 2, halfMargin, halfMargin, halfMargin)
                    2 -> setMargins(halfMargin, halfMargin, halfMargin * 2, halfMargin)
                }
            }
            viewBinding.root.setCardBackgroundColor(
                ContextCompat.getColor(
                    itemView.context,
                    R.color.lime_200
                )
            )
        }
    }
}