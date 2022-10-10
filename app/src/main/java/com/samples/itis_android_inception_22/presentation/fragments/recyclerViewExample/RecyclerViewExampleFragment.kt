package com.samples.itis_android_inception_22.presentation.fragments.recyclerViewExample

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.samples.itis_android_inception_22.R
import com.samples.itis_android_inception_22.databinding.FragmentRecyclerViewExampleBinding
import com.samples.itis_android_inception_22.presentation.adapters.InnerViewHolderAdapter
import com.samples.itis_android_inception_22.presentation.adapters.OuterViewHolderAdapter
import com.samples.itis_android_inception_22.presentation.models.ItemDataModel

class RecyclerViewExampleFragment : Fragment(R.layout.fragment_recycler_view_example) {

    private val viewBinding: FragmentRecyclerViewExampleBinding by viewBinding(FragmentRecyclerViewExampleBinding::bind)

    private var rvInnerViewHolderAdapter: InnerViewHolderAdapter? = null
    private var rvOuterViewHolderAdapter: OuterViewHolderAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // initRecyclerView()
        initOuterViewHolderAdapter()
    }

    override fun onDestroyView() {
        viewBinding.textItemsRv.adapter = null
        rvInnerViewHolderAdapter = null
        super.onDestroyView()
    }

    private fun initInnerViewHolderAdapter() {
        rvInnerViewHolderAdapter = InnerViewHolderAdapter().apply {
            items = listOf(
                ItemDataModel(headerText = "First Item Header", buttonText = "First Button"),
                ItemDataModel(headerText = "Second Item Header", buttonText = "Second Button"),
                ItemDataModel(headerText = "Third Item Header", buttonText = "Third Button"),
                ItemDataModel(headerText = "Fourth Item Header", buttonText = "Fourth Button"),
                ItemDataModel(headerText = "Fifth Item Header", buttonText = "Fifth Button"),
                ItemDataModel(headerText = "Sixth Item Header", buttonText = "Sixth Button"),
                ItemDataModel(headerText = "Sevenths Item Header", buttonText = "Sevenths Button"),
                ItemDataModel(headerText = "Eighth Item Header", buttonText = "Eighth Button"),
                ItemDataModel(headerText = "Ninth Item Header", buttonText = "Ninth Button"),
                ItemDataModel(headerText = "Tenth Item Header", buttonText = "Tenth Button"),
                ItemDataModel(headerText = "Eleventh Item Header", buttonText = "Eleventh Button"),
                ItemDataModel(headerText = "Twelfth Item Header", buttonText = "Twelfth Button"),
                ItemDataModel(headerText = "Thirteenth Item Header", buttonText = "Thirteenth Button"),
                ItemDataModel(headerText = "Fourteenth Item Header", buttonText = "Fourteenth Button"),
                ItemDataModel(headerText = "Fifteenth Item Header", buttonText = "Fifteenth Button")
            )
            onItemClickListener = { itemData ->
                Toast.makeText(requireContext(), itemData.headerText, Toast.LENGTH_SHORT).show()
            }
        }
        val manager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        viewBinding.textItemsRv.apply {
            layoutManager = manager
            adapter = rvInnerViewHolderAdapter
        }
    }

    private fun initOuterViewHolderAdapter() {
        rvOuterViewHolderAdapter = OuterViewHolderAdapter(
            itemsList = listOf(
                ItemDataModel(headerText = "First Item Header", buttonText = "First Button"),
                ItemDataModel(headerText = "Second Item Header", buttonText = "Second Button"),
                ItemDataModel(headerText = "Third Item Header", buttonText = "Third Button"),
                ItemDataModel(headerText = "Fourth Item Header", buttonText = "Fourth Button"),
                ItemDataModel(headerText = "Fifth Item Header", buttonText = "Fifth Button")
            )
            // В лямбде передается логика нажатия на элемент RecyclerView
        ) { data ->
            Toast.makeText(requireContext(), data.headerText, Toast.LENGTH_SHORT).show()
        }
        with(viewBinding) {
            textItemsRv.adapter = rvOuterViewHolderAdapter
            val itemDecorator = DividerItemDecoration(requireContext(), RecyclerView.VERTICAL).apply {
                ContextCompat.getDrawable(requireContext(), R.drawable.item_divider_bg)?.let { dividerDrawable ->
                    setDrawable(dividerDrawable)
                }
            }
            textItemsRv.addItemDecoration(itemDecorator)
        }
    }

    companion object {
        const val RECYCLER_VIEW_FRAGMENT_TAG = "RECYCLER_VIEW_FRAGMENT_TAG"

        fun getInstance(): RecyclerViewExampleFragment = RecyclerViewExampleFragment()
    }
}