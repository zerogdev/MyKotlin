/*
 * Designed and developed by 2020 skydoves (Jaewoong Eum)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.mysample.disneymotions.view.ui.main.viewholder

import android.view.View
import androidx.core.view.ViewCompat
import com.mysample.disneymotions.databinding.ItemPosterBinding
import com.mysample.disneymotions.model.Poster
import com.mysample.disneymotions.view.ui.main.details.PosterDetailActivity
import com.mysample.disneymotions.viewholder.bindings
import com.skydoves.baserecyclerviewadapter.BaseViewHolder

class PosterViewHolder(view: View) : BaseViewHolder(view) {

  private lateinit var data: Poster
  private val binding: ItemPosterBinding by bindings(view)

  override fun bindData(data: Any) {
    if (data is Poster) {
      this.data = data
      drawItemUI()
    }
  }

  override fun onClick(p0: View?) {
    PosterDetailActivity.startActivityModel(context(), binding.itemContainer, data)
  }

  private fun drawItemUI() {
    binding.apply {
      poster = data
    }
  }

//  override fun onClick(p0: View?) =
//    PosterDetailActivity.startActivityModel(context(), binding.itemContainer, data)

  override fun onLongClick(p0: View?) = false
}
