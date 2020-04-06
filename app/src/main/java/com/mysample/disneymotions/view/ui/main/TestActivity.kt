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

        Timber.tag("zerog").d("CoroutineScope start")
        CoroutineScope(Dispatchers.Main).launch {
            Timber.tag("zerog").d("CoroutineScope in launch")
            progress.visibility = View.VISIBLE

            //runBlocking으로 일시정지 테스트
            runBlocking {
                delay(2000)
            }

            //login
            userLogin()
            progress.visibility = View.GONE
        }
        Timber.tag("zerog").d("CoroutineScope end")
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
        Timber.tag("zerog").d("getFavoriteItemList")
        return "FavoriteItemList"
    }

    fun login(name:String, pwd: String): String {
        Timber.tag("zerog").d("login")
        return "id"
    }

    fun displayOnUI(favoritedResult: String) {
        Timber.tag("zerog").d("displayOnUI")
    }
}