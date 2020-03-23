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

package com.mysample.disneymotions.di

import com.mysample.disneymotions.network.DisneyClient
import com.mysample.disneymotions.network.DisneyService
import com.mysample.disneymotions.network.RequestInterceptor
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber

val networkModule = module {

  single {
    Timber.tag("zerog").d("networkModule-OkHttpClient.Builder()\n" +
            "      .addInterceptor(RequestInterceptor())\n" +
            "      .build()")
    OkHttpClient.Builder()
      .addInterceptor(RequestInterceptor())
      .build()
  }

  single {
    Timber.tag("zerog").d("networkModule-Retrofit.Builder()\n" +
            "      .client(get<OkHttpClient>())\n" +
            "      .baseUrl(\"https://gist.githubusercontent.com/skydoves/aa3bbbf495b0fa91db8a9e89f34e4873/raw/a1a13d37027e8920412da5f00f6a89c5a3dbfb9a/\")\n" +
            "      .addConverterFactory(GsonConverterFactory.create())\n" +
            "      .build()")
    Retrofit.Builder()
      .client(get<OkHttpClient>())
      .baseUrl("https://gist.githubusercontent.com/skydoves/aa3bbbf495b0fa91db8a9e89f34e4873/raw/a1a13d37027e8920412da5f00f6a89c5a3dbfb9a/")
      .addConverterFactory(GsonConverterFactory.create())
      .build()
  }

  single {
    Timber.tag("zerog").d("networkModule-get<Retrofit>().create(DisneyService::class.java) ")
    get<Retrofit>().create(DisneyService::class.java) }

  single {
    Timber.tag("zerog").d("networkModule-DisneyClient(get()) ")
    DisneyClient(get()) }
}
