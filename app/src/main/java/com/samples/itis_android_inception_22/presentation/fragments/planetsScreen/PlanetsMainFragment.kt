package com.samples.itis_android_inception_22.presentation.fragments.planetsScreen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.samples.itis_android_inception_22.R
import com.samples.itis_android_inception_22.databinding.FragmentPlanetsMainBinding
import com.samples.itis_android_inception_22.presentation.MainActivity
import com.samples.itis_android_inception_22.presentation.adapters.PlanetsRvAdapter
import com.samples.itis_android_inception_22.presentation.base.BaseFragment
import com.samples.itis_android_inception_22.presentation.models.PlanetDataModel

class PlanetsMainFragment : BaseFragment(R.layout.fragment_planets_main) {

    private val viewBinding: FragmentPlanetsMainBinding by viewBinding(FragmentPlanetsMainBinding::bind)

    private var rvAdapter: PlanetsRvAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setToolbarTitle(R.string.solar_system)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        val dataList = listOf(
            PlanetDataModel(
                planetName = getString(R.string.mercury),
                descriptionText = getString(R.string.mercury_description),
                imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/3/30/Mercury_in_color_-_Prockter07_centered.jpg/280px-Mercury_in_color_-_Prockter07_centered.jpg"
            ),
            PlanetDataModel(
                planetName = getString(R.string.venus),
                descriptionText = getString(R.string.venus_description),
                imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/0/08/Venus_from_Mariner_10.jpg/280px-Venus_from_Mariner_10.jpg"
            ),
            PlanetDataModel(
                planetName = getString(R.string.earth),
                descriptionText = getString(R.string.earth_description),
                imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/0/0d/Africa_and_Europe_from_a_Million_Miles_Away.png/270px-Africa_and_Europe_from_a_Million_Miles_Away.png"
            ),
            PlanetDataModel(
                planetName = getString(R.string.mars),
                descriptionText = getString(R.string.mars_description),
                imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/3/36/Mars_Valles_Marineris_EDIT.jpg/274px-Mars_Valles_Marineris_EDIT.jpg"
            ),
            PlanetDataModel(
                planetName = getString(R.string.jupiter),
                descriptionText = getString(R.string.venus_description),
                imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/0/08/Venus_from_Mariner_10.jpg/280px-Venus_from_Mariner_10.jpg"
            ),
            PlanetDataModel(
                planetName = getString(R.string.saturn),
                descriptionText = getString(R.string.venus_description),
                imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/0/08/Venus_from_Mariner_10.jpg/280px-Venus_from_Mariner_10.jpg"
            ),
            PlanetDataModel(
                planetName = getString(R.string.uranus),
                descriptionText = getString(R.string.venus_description),
                imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/0/08/Venus_from_Mariner_10.jpg/280px-Venus_from_Mariner_10.jpg"
            ),
            PlanetDataModel(
                planetName = getString(R.string.neptune),
                descriptionText = getString(R.string.venus_description),
                imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/0/08/Venus_from_Mariner_10.jpg/280px-Venus_from_Mariner_10.jpg"
            ),
            PlanetDataModel(
                planetName = getString(R.string.pluto),
                descriptionText = getString(R.string.venus_description),
                imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/0/08/Venus_from_Mariner_10.jpg/280px-Venus_from_Mariner_10.jpg"
            )
        )


        rvAdapter = PlanetsRvAdapter(dataList) { planetData ->
            (requireActivity() as? MainActivity)?.addFragment(
                PlanetDescriptionFragment.getInstance(planetData),
                PlanetDescriptionFragment.PLANET_DESCRIPTION_FRAGMENT_TAG
            )
        }
        viewBinding.planetsRv.adapter = rvAdapter
    }

    override fun onDestroyView() {
        viewBinding.planetsRv.adapter = null
        rvAdapter = null
        super.onDestroyView()
    }

    companion object {
        const val PLANETS_MAIN_FRAGMENT_TAG = "PLANETS_MAIN_FRAGMENT_TAG"

        fun getInstance() = PlanetsMainFragment()
    }
}