package com.mysample.disneymotions.view.ui.main

import android.os.Bundle
import com.mysample.disneymotions.R
import com.mysample.disneymotions.base.DatabindingActivity
import com.mysample.disneymotions.coroutines.*
import com.mysample.disneymotions.databinding.MainActivityBinding
import timber.log.Timber

class MainActivity : DatabindingActivity() {

    private val binding: MainActivityBinding by binding(R.layout.main_activity)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //mvvm, viewdatabinding, lifecycle
        Timber.tag("LYK").d("main1")

        binding.apply {
            Timber.tag("LYK").d("main2")
            pagerAdapter = MainPagerAdapter(supportFragmentManager)
            navigation = mainBottomNavigation
        }



        //coroutine
        Timber.tag("coroutine").d("coroutine main started:${ Thread.currentThread()}")
//        launchInGlobalScope()// 비동기
//        runBlockingExample() //동기식으로 launch가 실행완료될때까지 블럭된다
//        runBlockingYieldExample()
//        sumAll()
        dispatcherExample()
        Timber.tag("coroutine").d("coroutine launchInGlobalScope():${ Thread.currentThread()}")
        Thread.sleep(2000L)
        Timber.tag("coroutine").d("coroutine terminated:${ Thread.currentThread()}")

    }
}
