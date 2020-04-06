package com.mysample.disneymotions.view.ui.main

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.mysample.disneymotions.R
import com.mysample.disneymotions.etc.sharedpreferences.AppPreferences
import com.mysample.disneymotions.test.*
import kotlinx.android.synthetic.main.activity_test.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.delayEach
import kotlinx.coroutines.flow.flowOf
import okhttp3.internal.wait
import timber.log.Timber
import java.math.BigDecimal

class TestActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        val userName = UserName.createUsername("zerog")
        val userProfile = IUserName.create("zerog")
        Timber.tag("zerog").d("userProfile.name=${userProfile.name}")

        val options: Array<PaymentOption> = PaymentOption.values()
        Timber.tag("zerog").d( options.map { it.name }.toString())// [CASH, CARD, TRANSFER]

        val option: PaymentOption = PaymentOption.valueOf("CARD")
        Timber.tag("zerog").d(option.toString())// CARD

        val options2: Array<PaymentOption> = enumValues<PaymentOption>()
        Timber.tag("zerog").d( options2.map { it.name }.toString())// [CASH, CARD, TRANSFER]

        val option2: PaymentOption = enumValueOf<PaymentOption>("CARD")
        Timber.tag("zerog").d(option2.toString())// CARD

        printEnumValues<PaymentOption>()

        process(CashPayment(BigDecimal.ONE, 1))


        val appPreferences:AppPreferences = AppPreferences(getSharedPreferences("a", Context.MODE_PRIVATE))
        appPreferences.incrementSessionCount()
        if (appPreferences.isFirstSession()) {
            Timber.tag("zerog").d("first session.")
        } else {
            Timber.tag("zerog").d("session(${appPreferences.sessionCount})")
        }

        //// 코루틴 launch ////
        //참고링크: https://medium.com/mindful-engineering/fast-lane-to-coroutines-bce8388ed82b
        /*
        Timber.tag("zerog").d("CoroutineScope start")

        //이것이 먼저 실행될수도있고 아래 스코프가 먼저 실행될수도 있다.
        CoroutineScope(Dispatchers.Main).launch {
            Timber.tag("zerog").d("CoroutineScope in launch -start")
            progress.visibility = View.VISIBLE

            //runBlocking으로 일시정지 테스트
//            runBlocking {
//                delay(2000)
                //fetch the user details background thread using IO dispatcher
//            }

            //login
            userLogin()
            progress.visibility = View.GONE
            Timber.tag("zerog").d("CoroutineScope in launch - end")
        }
        Timber.tag("zerog").d("CoroutineScope end")

        //이것이 먼저 실행될수도있고 아래 스코프가 먼저 실행될수도 있다.
        CoroutineScope(Dispatchers.Main).launch {
            generalDetails()
        }
        */

        //// 코루틴 await, async ////
        CoroutineScope(Dispatchers.Main).launch {
            Timber.tag("zerog").d("CoroutineScope await/async start")
            progress.visibility = View.VISIBLE
            val user = userLogin()
            val fr = getFavoriteItemListAsync("a")
            val pr = getPurchasedItemListAsync("a")

            val itemsToShow = fr.await()
            val itemsToShow2 = pr.await()
            Timber.tag("zerog").d("CoroutineScope await/async end")
        }

        //응용프로그램이 종료될때까지 범
        GlobalScope.launch {  }
        //ViewModelScope 도있으며 androidx.lifecycle:lifecycle-viewmodel-ktx 에 있고 ViewModel이 파괴될때 취소

    }

    suspend fun userLogin() {
        //Dispatchers.IO 로 변경-네트워크 요청시 사용.
        withContext(Dispatchers.IO) {
            val user = login("1111", "2222")
            Timber.tag("zerog").d("userLogin")

            val favoritedResult = getFavoriteItemList(user)
            displayOnUI(favoritedResult)
        }
    }

    suspend fun getFavoriteItemList(user:String): String {
        withContext(Dispatchers.IO) {
            // fetching favorited items from network
            Timber.tag("zerog").d("getFavoriteItemList")
        }

        return "FavoriteItemList"
    }

    suspend fun getFavoriteItemListAsync(user:String) =
        CoroutineScope(Dispatchers.IO).async {
            Timber.tag("zerog").d("getFavoriteItemList")
        }


    suspend fun getPurchasedItemListAsync(user:String) =
        CoroutineScope(Dispatchers.IO).async {
            // fetching favorited items from network
            Timber.tag("zerog").d("getPurchasedItemList")
        }


    fun login(name:String, pwd: String): String {
        Timber.tag("zerog").d("login")
        return "id"
    }

    fun displayOnUI(favoritedResult: String) {
        Timber.tag("zerog").d("displayOnUI")
    }

    suspend fun generalDetails() {
        withContext(Dispatchers.IO) {
            // fetching general details from network
            Timber.tag("zerog").d("Launch general details")
        }
    }

}


