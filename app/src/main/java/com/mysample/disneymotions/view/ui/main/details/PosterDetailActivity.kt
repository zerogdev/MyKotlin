package com.mysample.disneymotions.view.ui.main.details

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import com.mysample.disneymotions.R
import com.mysample.disneymotions.base.DatabindingActivity
import com.mysample.disneymotions.base.DatabindingFragment
import com.mysample.disneymotions.databinding.ActivityPosterDetailBinding
import com.mysample.disneymotions.model.Poster
import org.koin.android.viewmodel.ext.android.getViewModel

class PosterDetailActivity : DatabindingActivity() {

    private val binding: ActivityPosterDetailBinding by binding(R.layout.activity_poster_detail)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val poster = getViewModel<PosterDetailViewModel>().getPoster(intent.getLongExtra(posterKey, 0))
        binding.apply {
            this.poster = poster
            activity = this@PosterDetailActivity
            container = detailContainer
            fab = fabMore
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        private const val posterKey = "posterKey"
        fun startActivityModel(context: Context?, startView: View, poster: Poster) {
            if (context is Activity) {
                val intent = Intent(context, PosterDetailActivity::class.java)
                intent.putExtra(posterKey,poster.id)
                context.startActivity(intent)
            }
        }
    }
}