package com.mysample.disneymotions.view.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mysample.disneymotions.R
import com.mysample.disneymotions.test.*
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.delayEach
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
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

//        runBlocking {
//            val numbersFlow = flowOf(1, 2, 3).delayEach(1000)
//            val lettersFlow = flowOf("A", "B", "C").delayEach(2000)
//
//            numbersFlow.combine(lettersFlow) { number, letter ->
//                "$number$letter"
//            }
//        }
    }
}