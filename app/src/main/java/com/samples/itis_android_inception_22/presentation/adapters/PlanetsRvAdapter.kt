package com.samples.itis_android_inception_22.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.samples.itis_android_inception_22.R
import com.samples.itis_android_inception_22.databinding.ItemPlanetMainBinding
import com.samples.itis_android_inception_22.presentation.models.PlanetDataModel

class PlanetsRvAdapter(
    private val planetsList: List<PlanetDataModel>,
    private val action: ((PlanetDataModel) -> Unit)
) : RecyclerView.Adapter<PlanetsRvAdapter.PlanetViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlanetViewHolder {
        return PlanetViewHolder(
            viewBinding = ItemPlanetMainBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PlanetViewHolder, position: Int) {
        holder.bindItem(planetsList[position])
    }

    override fun getItemCount(): Int = planetsList.size

    fun setNewItems(newList: List<PlanetDataModel>) {
        val diffUtilsCallback = object : DiffUtil.Callback() {

            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                val oldItemName = planetsList[oldItemPosition].planetName
                val newItemName = newList[newItemPosition].planetName
                return oldItemName == newItemName
            }

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                val oldItemDesc = planetsList[oldItemPosition].descriptionText
                val newItemDesc = newList[newItemPosition].descriptionText
                return oldItemDesc == newItemDesc
            }

            override fun getOldListSize(): Int = planetsList.size

            override fun getNewListSize(): Int = newList.size
        }

        val diffResult = DiffUtil.calculateDiff(diffUtilsCallback)
        diffResult.dispatchUpdatesTo(this@PlanetsRvAdapter)
    }

    inner class PlanetViewHolder(private val viewBinding: ItemPlanetMainBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {

        init {
            viewBinding.root.setOnClickListener {
                planetsList[bindingAdapterPosition].isChecked = true
                notifyItemChanged(bindingAdapterPosition)
                action(planetsList[bindingAdapterPosition])
            }
        }

        fun bindItem(planetItem: PlanetDataModel) {
            with(viewBinding) {
                dividerView.visibility =
                    if (bindingAdapterPosition == (planetsList.size - 1)) View.INVISIBLE else View.VISIBLE
                planetNameTv.text = planetItem.planetName
                val bgColor = if (planetItem.isChecked) R.color.gray_50 else R.color.white
                root.setBackgroundColor(ContextCompat.getColor(itemView.context, bgColor))
            }
        }
    }
}