package com.mysample.disneymotions.view.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mysample.disneymotions.R
import com.mysample.disneymotions.base.DatabindingFragment
import com.mysample.disneymotions.databinding.FragmentHomeBinding
import com.mysample.disneymotions.view.ui.main.adapter.PosterAdapter
import com.mysample.disneymotions.view.ui.main.details.PosterDetailActivity
import org.koin.android.viewmodel.ext.android.getViewModel
import timber.log.Timber

class HomeFragment : DatabindingFragment(){

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding<FragmentHomeBinding>(inflater, R.layout.fragment_home, container).apply {
            Timber.tag("zerog").d("HomeFragment-binding->getViewModel<MainViewModel>()")

            viewModel = getViewModel<MainViewModel>().apply {
                Timber.tag("zerog").d("HomeFragment-fetchDisneyPosterList(")
                fetchDisneyPosterList()
            }
            lifecycleOwner = this@HomeFragment
            adapter = PosterAdapter()


            btn1.setOnClickListener {
                val intent = Intent(context, TestActivity::class.java)
                startActivity(intent)
            }
        }.root
    }
}