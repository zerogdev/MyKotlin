package com.mysample.disneymotions.base

import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import timber.log.Timber

abstract class DatabindingActivity : AppCompatActivity() {
    protected inline fun<reified T: ViewDataBinding> binding(@LayoutRes resId: Int) : Lazy<T>
    = lazy {
        Timber.tag("LYK").d("DatabindingActivity-DataBindingUtil.setContentView<T>(this, resId) ")
        DataBindingUtil.setContentView<T>(this, resId) }
}