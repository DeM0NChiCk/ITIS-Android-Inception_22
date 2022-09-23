package com.samples.itis_android_inception_22.presentation.fragments.paramsPage

import android.os.Bundle
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import by.kirich1409.viewbindingdelegate.viewBinding
import com.samples.itis_android_inception_22.R
import com.samples.itis_android_inception_22.databinding.FragmentLayoutParamsBinding
import com.samples.itis_android_inception_22.presentation.base.BaseActivity
import com.samples.itis_android_inception_22.presentation.base.BaseFragment
import com.samples.itis_android_inception_22.presentation.fragments.editTextPage.EditTextPageFragment
import com.samples.itis_android_inception_22.presentation.models.ViewDimensionsModel
import com.samples.itis_android_inception_22.utils.dpToPx

class LayoutParamsFragment : BaseFragment(R.layout.fragment_layout_params) {

    private val viewBinding: FragmentLayoutParamsBinding by viewBinding(FragmentLayoutParamsBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewSizes = calculateViewSize()
        changeViewsParams(viewData = viewSizes)
        initClickListeners()
    }

    /* Предположим, что нам нужно изменить размер ImageView
     * 2/3 от ширины экрана по горизонтали
     * 1/2 от ширины экрана по вертикали
     * */
    private fun calculateViewSize(): ViewDimensionsModel {
        val availableWidth = requireContext().resources.displayMetrics.widthPixels

        val viewWidth = availableWidth * 0.6667f
        val viewHeight = availableWidth / 2f

        return ViewDimensionsModel(
            horizontalSize = viewWidth.toInt(),
            verticalSize = viewHeight.toInt()
        )
    }

    private fun changeViewsParams(viewData: ViewDimensionsModel) {
        with(viewBinding) {
            // LayoutParams можно не кастить к какому-то определенному виду, если вам просто нужно задать размеры
            headerIv.layoutParams?.apply {
                // Применение просчитанных размеров к ImageView
                width = viewData.horizontalSize
                height = viewData.verticalSize
            }
            /* Т.к. ImageView находится внутри ConstraintLayout-а, кастить LayoutParams нужно к
             * ConstraintLayout.LayoutParams.
             * Можно совместить с предыдущим шагом и так же задавать размеры прямо здесь
             *
             * */
            (headerIv.layoutParams as? ConstraintLayout.LayoutParams)?.apply {
                // Убираем нижний constraint ImageView
                bottomToBottom = ConstraintLayout.LayoutParams.UNSET
                // width = viewData.horizontalSize
                // height = viewData.verticalSize

                // Задаем отступ сверху, с левого и правого краев
                val baseMargin = requireContext().dpToPx(8f).toInt()
                topMargin = baseMargin
                marginStart = baseMargin * 2
                marginEnd = baseMargin * 2
            }

            // Поменяем top constraint второй кнопки - привяжем к низу imageView
            (pageSecondBtn.layoutParams as? ConstraintLayout.LayoutParams)?.topToBottom = headerIv.id

            (pageFirstBtn.layoutParams as? ConstraintLayout.LayoutParams)?.apply {
                // Убираем верхний constraint
                topToBottom = ConstraintLayout.LayoutParams.UNSET
                // Привязываем кнопку к низу родительского Constraint Layout-а
                bottomToBottom = ConstraintLayout.LayoutParams.PARENT_ID
            }

        }
    }

    private fun initClickListeners() {
        with(viewBinding) {
            pageFirstBtn.setOnClickListener {
                (requireActivity() as? BaseActivity)?.replaceFragment(
                    EditTextPageFragment.getInstance(),
                    EditTextPageFragment.EDIT_TEXT_PAGE_FRAGMENT_TAG
                )
            }
        }
    }

    companion object {
        const val LAYOUT_PARAMS_FRAGMENT_TAG = "LAYOUT_PARAMS_FRAGMENT_TAG"

        fun getInstance() = LayoutParamsFragment()
    }
}