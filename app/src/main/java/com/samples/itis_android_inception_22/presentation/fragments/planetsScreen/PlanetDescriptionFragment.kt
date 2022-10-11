package com.samples.itis_android_inception_22.presentation.fragments.planetsScreen

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.samples.itis_android_inception_22.R
import com.samples.itis_android_inception_22.databinding.FragmentPlanetDescriptionBinding
import com.samples.itis_android_inception_22.presentation.base.BaseFragment
import com.samples.itis_android_inception_22.presentation.models.PlanetDataModel

class PlanetDescriptionFragment : BaseFragment(R.layout.fragment_planet_description) {

    private val viewBinding: FragmentPlanetDescriptionBinding by viewBinding(FragmentPlanetDescriptionBinding::bind)

    private fun getPlanetData(): PlanetDataModel = arguments?.getParcelable(PLANET_DATA_TAG)
        ?: throw IllegalArgumentException("Planet data is null")

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val planetInfo = getPlanetData()
        with(viewBinding) {

            progressBar.visibility = View.VISIBLE
            Glide.with(this@PlanetDescriptionFragment)
                .load(planetInfo.imageUrl)
                .addListener(object : RequestListener<Drawable> {

                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        progressBar.visibility = View.GONE
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        progressBar.visibility = View.GONE
                        return false
                    }
                })
                .into(planetImageIv)

            planetNameTv.text = planetInfo.planetName
            planetDescriptionTv.text = planetInfo.descriptionText
        }
    }

    companion object {
        const val PLANET_DESCRIPTION_FRAGMENT_TAG = "PLANET_DESCRIPTION_FRAGMENT_TAG"
        private const val PLANET_DATA_TAG = "PLANET_DATA_TAG"

        fun getInstance(planetData: PlanetDataModel) = PlanetDescriptionFragment().apply {
            arguments = bundleOf(PLANET_DATA_TAG to planetData)
        }
    }
}