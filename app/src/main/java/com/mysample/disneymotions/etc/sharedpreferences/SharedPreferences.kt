package com.mysample.disneymotions.etc.sharedpreferences

import android.content.SharedPreferences

/**
 * by 위임 사용, 인터페이스 사용
 *
 * https://medium.com/@nicholas.rose/a-smarter-sharedpreferences-with-kotlin-ac5ecd2056c0
 */



//고전적인 방법
class SessionManager(private val sharedPreferences: SharedPreferences) {
    val sessionCount: Int
    get() = sharedPreferences.getInt(KEY_SESSION_COUNT, 0)

    fun incrementSessionCount() {
        sharedPreferences.edit()
            .putInt(KEY_SESSION_COUNT, sessionCount + 1)
            .apply()
    }

    companion object {
        private const val KEY_SESSION_COUNT = "KEY_SESSION_COUNT"
    }
}

//인터페이스
interface SessionCounter {
    val sessionCount: Int
    fun isFirstSession() = sessionCount == 1
}

//sessionCount를 캡슐화하고 위임을 통해서 언제든지 확장 가능하도록 한 유틸 클래스
class AppPreferences(sharedPreferences: SharedPreferences) : SharedPreferences by sharedPreferences, SessionCounter {
    override val sessionCount: Int
        get() = getInt(KEY_SESSION_COUNT, 0)

    fun incrementSessionCount() {
        edit()
            .putInt(KEY_SESSION_COUNT, sessionCount + 1)
            .apply()
    }
    companion object {
        private const val KEY_SESSION_COUNT = "KEY_SESSION_COUNT"
    }

}