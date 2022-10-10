package com.samples.itis_android_inception_22.presentation.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.samples.itis_android_inception_22.R
import com.samples.itis_android_inception_22.databinding.ItemRecyclerExampleBinding
import com.samples.itis_android_inception_22.presentation.models.ItemDataModel

class InnerViewHolderAdapter : RecyclerView.Adapter<InnerViewHolderAdapter.ItemRecyclerExampleViewHolder>() {

    var items: List<ItemDataModel> = listOf()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var onItemClickListener: ((ItemDataModel) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemRecyclerExampleViewHolder {
        return ItemRecyclerExampleViewHolder(
            viewBinding = ItemRecyclerExampleBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemRecyclerExampleViewHolder, position: Int) {
        holder.bindItem(items[position])
    }

    override fun getItemCount(): Int = items.size

    // Не забудьте обозначить вложенный класс через ключевое слово inner, чтобы у вас была возможность обращаться
    // к полям внешнего класса
    inner class ItemRecyclerExampleViewHolder(
        private val viewBinding: ItemRecyclerExampleBinding
    ) : RecyclerView.ViewHolder(viewBinding.root) {

        init {
            with(viewBinding) {
                root.setOnClickListener {
                    val currentCheckState = items[bindingAdapterPosition].isChecked
                    items[bindingAdapterPosition].isChecked = !currentCheckState
                    onItemClickListener?.invoke(items[bindingAdapterPosition])
                    notifyItemChanged(bindingAdapterPosition)
                }
                itemCb.setOnCheckedChangeListener { _, isChecked ->
                    items[bindingAdapterPosition].isChecked = isChecked
                    /* Не нужно делать дополнительный вызов notifyItemChanged, так как Check Box поменяется сам собой,
                     Перерисовывать вьюшку нам не нужно, поэтому можно просто менять значение из isChecked в data списке,
                     чтобы при нажатии на весь элемент логика продолжала корректно работать
                     */
                    onItemClickListener?.invoke(items[bindingAdapterPosition])
                }
            }
        }

        fun bindItem(item: ItemDataModel) {
            with(viewBinding) {
                itemIv.setImageDrawable(ContextCompat.getDrawable(itemView.context, R.mipmap.ic_launcher_round))
                itemHeaderTv.text = item.headerText
                recyclerActionBtn.text = item.buttonText
                itemCb.isChecked = item.isChecked
            }
        }
    }
}