package com.mysample.disneymotions.test

import timber.log.Timber
import java.math.BigDecimal

/**
 * 참고: https://blog.kotlin-academy.com/enum-vs-sealed-class-which-one-to-choose-dc92ce7a4df5
 *
 * sealed class 와  enum 비교
 */


enum class PaymentOption {
    CASH,
    CARD,
    TRANSFER;

    var commission: BigDecimal = BigDecimal.ZERO
}

enum class PaymentOption2 {
    CASH {
        override fun startPayment(transaction: String) {
            TODO("Not yet implemented")
        }
    },
    CARD {
        override fun startPayment(transaction: String) {
            TODO("Not yet implemented")
        }
    },
    TRANSFER {
        override fun startPayment(transaction: String) {
            TODO("Not yet implemented")
        }
    }
    ;

    //이건 잘 사용되지 않는다
    abstract fun startPayment(transaction: String)
}

/**
 * 위코드 보다는 함수를 받아 처리하는것이 더 편리하다
 */
enum class PaymentOption3(val startPayment: (String) -> Unit) {
    CASH(::showCashPaimentInfo),
    CARD(::moveToCardPaymentPage),
    TRANSFER({
        showMoneyTransferInfo()
        setupPaymentWatcher(it)
    })
}

/**
 * 위코드 보다 확장함수로 처리하는것이 더 편리하다
 */
fun PaymentOption.startPayment(transaction: String) {
    when (this) {
        PaymentOption.CASH -> showCashPaimentInfo(transaction)
        PaymentOption.CARD -> moveToCardPaymentPage(transaction)
        PaymentOption.TRANSFER -> {
            showMoneyTransferInfo()
            setupPaymentWatcher(transaction)
        }
    }
}

/**
 * enum 이 편리한점
 * - values(), enumValueOf() 함수를 사용하여 유형별로 모든 항목을 가져올수 있음
 * - 직력화, 역직렬화할 수 있음
 */
inline fun <reified T: Enum<T>> printEnumValues() {
    for (value in enumValues<T>()) {
        Timber.tag("zerog").d(value.toString())
    }
}

/**
 * sealed class
 * - 서브 클래스에서 각각 데이터를 보유할 수 있음
 *
 * */
sealed class Payment
data class CashPayment(val amount: BigDecimal, val pointId: Int): Payment()
data class CardPayment(val amount: BigDecimal, val orderId: Int): Payment()
data class BankTransfer(val amount: BigDecimal, val orderId: Int): Payment()

fun process(payment: Payment) {
    when (payment) {
        is CashPayment -> {
            Timber.tag("zerog").d("CashPayment:"+payment.amount.toString())
        }
        is CardPayment -> {
            Timber.tag("zerog").d("CardPayment"+payment.amount.toString())
        }
        is BankTransfer -> {
            Timber.tag("zerog").d("BankTransfer"+payment.amount.toString())
        }
    }
}


fun showCashPaimentInfo(data: String):Unit {

}

fun moveToCardPaymentPage(data: String):Unit {

}

fun showMoneyTransferInfo():Unit {

}

fun setupPaymentWatcher(data: String):Unit {

}
