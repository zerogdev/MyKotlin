package com.mysample.disneymotions.binding

import android.widget.Toast
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.mysample.disneymotions.model.Poster
import com.mysample.disneymotions.view.ui.main.adapter.PosterAdapter
import com.skydoves.baserecyclerviewadapter.BaseAdapter
import com.skydoves.whatif.whatIfNotNull
import com.skydoves.whatif.whatIfNotNullOrEmpty
import timber.log.Timber


@BindingAdapter("adapter")
fun bindAdapter(view: RecyclerView, baseAdapter: BaseAdapter) {
    Timber.tag("zerog").d("bindAdapter-view.adapter = baseAdapter")
    view.adapter = baseAdapter
}

@BindingAdapter("toast")
fun bindToast(view: RecyclerView, text: LiveData<String>) {
    text.value.whatIfNotNull {
        Toast.makeText(view.context, it, Toast.LENGTH_SHORT).show()
    }
}

@BindingAdapter("adapterPosterLIst")
fun bindAdapterPosterLIst(view: RecyclerView, posters: List<Poster>?) {
    Timber.tag("zerog").d("bindAdapterPosterLIst-(view.adapter as? PosterAdapter)?.addPosterList(it) ${posters?.size}")
    posters.whatIfNotNullOrEmpty {
        (view.adapter as? PosterAdapter)?.addPosterList(it)
    }
}

