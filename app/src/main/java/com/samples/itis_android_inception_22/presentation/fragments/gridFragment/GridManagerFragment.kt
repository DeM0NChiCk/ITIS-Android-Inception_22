package com.samples.itis_android_inception_22.presentation.fragments.gridFragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.samples.itis_android_inception_22.R
import com.samples.itis_android_inception_22.databinding.FragmentGridManagerBinding
import com.samples.itis_android_inception_22.presentation.adapters.GridManagerAdapter
import com.samples.itis_android_inception_22.presentation.base.BaseFragment
import com.samples.itis_android_inception_22.presentation.models.GridItemDimensionsModel
import com.samples.itis_android_inception_22.utils.dpToPx

class GridManagerFragment : BaseFragment(R.layout.fragment_grid_manager) {

    private val viewBinding: FragmentGridManagerBinding by viewBinding(FragmentGridManagerBinding::bind)

    private var rvAdapter: GridManagerAdapter? = null

    private var gridDimensions: GridItemDimensionsModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        gridDimensions = calculateViewSizes()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setToolbarTitle("Staggered Grid")
        initRecyclerView()
    }

    private fun initRecyclerView() {
        val layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL).apply {
                gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS
            }
        rvAdapter =
            GridManagerAdapter(
                gridItems = listOf(
                    1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18
                ),
                dimension = gridDimensions
                    ?: throw IllegalArgumentException("Grid dimensions model is null"),
                clickAction = { position ->
                    Toast.makeText(
                        requireContext(),
                        "Item at ${position + 1} position is clicked",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            )
        with(viewBinding) {
            blocksRv.layoutManager = layoutManager
            blocksRv.adapter = rvAdapter
        }
    }

    /* Референсные значения для верстки:
     * Экран устройства - 360 x 760 dp
     * Горизонтальная плашка - 328 х 164 dp
     * Вертикальная плашка - 164 x 260 dp
     *
     * Соотношения (за основу берется ширина горизонтальной плашки):
     * -- 328 / 164 = 2
     * -- 328 / 260 ~ 1.2615
     */
    private fun calculateViewSizes(): GridItemDimensionsModel {
        val metrics = resources.displayMetrics

        val horizontalItemWidth = metrics.widthPixels
        val horizontalItemHeight = horizontalItemWidth / 2f
        val verticalItemHeight = horizontalItemWidth / 1.2615f

        return GridItemDimensionsModel(
            horizontalItemWidth = horizontalItemWidth,
            horizontalItemHeight = horizontalItemHeight.toInt(),
            verticalItemWidth = horizontalItemHeight.toInt(),
            verticalItemHeight = verticalItemHeight.toInt(),
            halfMargin = requireContext().dpToPx(8f).toInt()
        )
    }

    companion object {
        const val GRID_MANAGER_FRAGMENT_TAG = "GRID_MANAGER_FRAGMENT_TAG"

        fun getInstance() = GridManagerFragment()
    }
}