package com.mysample.disneymotions.binding

import android.graphics.Color
import android.transition.TransitionManager
import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.databinding.BindingAdapter
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.transition.MaterialArcMotion
import com.google.android.material.transition.MaterialContainerTransform
import com.mysample.disneymotions.extensions.gone
import com.mysample.disneymotions.extensions.visible


@BindingAdapter("loadImage")
fun bindLoadImage(view: AppCompatImageView, url: String) {
    Glide.with(view.context)
        .load(url)
        .into(view)
}

@BindingAdapter("bindNavigation")
fun bindNavigation(view: ViewPager, navigationView: BottomNavigationView) {
    view.addOnPageChangeListener( object : ViewPager.OnPageChangeListener {
        override fun onPageScrollStateChanged(state: Int) = Unit

        override fun onPageScrolled(
            position: Int,
            positionOffset: Float,
            positionOffsetPixels: Int
        ) = Unit

        override fun onPageSelected(position: Int) {
            navigationView.menu.getItem(position).isChecked = true
        }
    })
}

@BindingAdapter("pagerAdapter")
fun bindPagerAdapter(view: ViewPager, adapter: PagerAdapter) {
    view.adapter = adapter
    view.offscreenPageLimit = 3
}

@BindingAdapter("bindFab")
fun bindAppBarLayoutWithFab(appBarLayout: AppBarLayout, fab: FloatingActionButton) {
    appBarLayout.addOnOffsetChangedListener(
        AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->

        }
    )
}


@BindingAdapter("transformFab", "transformContainer")
fun bindTransformFab(view: View, fab: FloatingActionButton, container: CoordinatorLayout) {
    fab.setOnClickListener {
        // Begin the transition by changing properties on the start and end views or
        // removing/adding them from the hierarchy.
        fab.tag = View.GONE
        TransitionManager.beginDelayedTransition(container, getTransform(fab, view))
        fab.gone()
        view.visible()
    }

    view.setOnClickListener {
        fab.tag = View.VISIBLE
        TransitionManager.beginDelayedTransition(container, getTransform(view, fab))
        fab.visible()
        view.gone()
    }
}

internal fun getTransform(mStartView: View, mEndView: View): MaterialContainerTransform {
    return MaterialContainerTransform(mStartView.context).apply {
        startView = mStartView
        endView = mEndView
        pathMotion = MaterialArcMotion()
        duration = 650
        scrimColor = Color.TRANSPARENT
    }
}