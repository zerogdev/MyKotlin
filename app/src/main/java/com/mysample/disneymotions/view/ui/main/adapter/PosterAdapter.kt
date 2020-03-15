package com.mysample.disneymotions.view.ui.main.adapter

import android.view.View
import com.mysample.disneymotions.R
import com.mysample.disneymotions.model.Poster
import com.mysample.disneymotions.view.ui.main.viewholder.PosterViewHolder
import com.skydoves.baserecyclerviewadapter.BaseAdapter
import com.skydoves.baserecyclerviewadapter.SectionRow

class PosterAdapter : BaseAdapter() {

    init {
        addSection(arrayListOf<Poster>())
    }

    fun addPosterList(posters: List<Poster>) {
        sections().first().run {
            clear()
            addAll(posters)
            notifyDataSetChanged()
        }
    }

    override fun layout(sectionRow: SectionRow): Int = R.layout.item_poster

    override fun viewHolder(layout: Int, view: View) = PosterViewHolder(view)
}