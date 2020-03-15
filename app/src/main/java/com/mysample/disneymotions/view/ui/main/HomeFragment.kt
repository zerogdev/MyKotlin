package com.mysample.disneymotions.view.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mysample.disneymotions.R
import com.mysample.disneymotions.base.DatabindingFragment
import com.mysample.disneymotions.databinding.FragmentHomeBinding
import com.mysample.disneymotions.view.ui.main.adapter.PosterAdapter
import org.koin.android.viewmodel.ext.android.getViewModel
import timber.log.Timber

class HomeFragment : DatabindingFragment(){

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding<FragmentHomeBinding>(inflater, R.layout.fragment_home, container).apply {
            Timber.tag("LYK").d("HomeFragment-binding->getViewModel<MainViewModel>()")

            viewModel = getViewModel<MainViewModel>().apply {
                Timber.tag("LYK").d("HomeFragment-fetchDisneyPosterList(")
                fetchDisneyPosterList()
            }
            lifecycleOwner = this@HomeFragment
            adapter = PosterAdapter()
        }.root
    }
}