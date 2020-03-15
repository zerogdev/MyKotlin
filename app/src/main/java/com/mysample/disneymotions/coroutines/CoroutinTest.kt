package com.mysample.disneymotions.coroutines

import kotlinx.coroutines.*
import timber.log.Timber
import java.time.ZonedDateTime
import java.time.temporal.ChronoUnit
import kotlin.coroutines.RestrictsSuspension
import kotlin.coroutines.suspendCoroutine

fun now() = ZonedDateTime.now().toLocalTime().truncatedTo(ChronoUnit.MILLIS)

fun launchInGlobalScope() {
    GlobalScope.launch {
        Timber.tag("coroutine").d("coroutine started:${ Thread.currentThread()}")
    }
}

fun runBlockingExample() {
    runBlocking {
        launch {
            Timber.tag("coroutine").d("coroutine GlobalScope.launch started:${ Thread.currentThread()}")
        }
    }
}

fun runBlockingYieldExample() {
    runBlocking {
        launch {
            Timber.tag("coroutine").d("1:${ Thread.currentThread()}")
            yield()
            Timber.tag("coroutine").d("3:${ Thread.currentThread()}")
            yield()
            Timber.tag("coroutine").d("5:${ Thread.currentThread()}")
        }

        Timber.tag("coroutine").d("after first launch:${ Thread.currentThread()}")

        launch {
            Timber.tag("coroutine").d("2:${ Thread.currentThread()}")
            delay(1000)
//            yield()
            Timber.tag("coroutine").d("4:${ Thread.currentThread()}")
            delay(1000)
//            yield()
            Timber.tag("coroutine").d("6:${ Thread.currentThread()}")
        }

        Timber.tag("coroutine").d("after second launch:${ Thread.currentThread()}")

    }
}

fun sumAll() {
    runBlocking {
        val d1 = async { delay(1000L); 1 }
        Timber.tag("coroutine").d("after async(d1):${ Thread.currentThread()}")
        val d2 = async { delay(2000L); 2 }
        Timber.tag("coroutine").d("after async(d2):${ Thread.currentThread()}")
        val d3 = async { delay(3000L); 3 }
        Timber.tag("coroutine").d("after async(d3):${ Thread.currentThread()}")

        Timber.tag("coroutine").d("1+2+3=${d1.await() + d2.await() + d3.await()}:${ Thread.currentThread()}")
        Timber.tag("coroutine").d("after await all & add:${ Thread.currentThread()}")
    }
}

fun dispatcherExample() {
    runBlocking {
        //부모 컨텍스트를 사용: 메인스레드
        launch {
            Timber.tag("coroutine").d("main runBlocking: I'm working in thread:${ Thread.currentThread()}")
        }
        //특정 스레드에 종속되지 않음: 메인스레드
        launch(Dispatchers.Unconfined) {
            Timber.tag("coroutine").d("Unconfined : I'm working in thread:${ Thread.currentThread()}")
        }

        //기본 디스패처를 사용
        launch(Dispatchers.Default) {
            Timber.tag("coroutine").d("Default : I'm working in thread:${ Thread.currentThread()}")
        }

        //새 스레드를 사용
        launch(newSingleThreadContext("MyOwnThread")) {
            Timber.tag("coroutine").d("newSingleThreadContext : I'm working in thread:${ Thread.currentThread()}")
        }
    }
}

suspend fun yieldThreeTimes() {
    Timber.tag("coroutine").d("1:${ Thread.currentThread()}")
    delay(1000L)
    yield()
    Timber.tag("coroutine").d("2:${ Thread.currentThread()}")
    delay(1000L)
    yield()
    Timber.tag("coroutine").d("3:${ Thread.currentThread()}")
    delay(1000L)
    yield()
    Timber.tag("coroutine").d("4:${ Thread.currentThread()}")
}

fun suspendExample() {
    GlobalScope.launch {
        yieldThreeTimes()
    }
}

interface Generator<out R, in T> {
    fun next(param: T) : R?
}

@RestrictsSuspension
interface GeneratorBuilder<in T, R> {
    suspend fun yield(value: T): R
    suspend fun yieldAll(generator: Generator<T, R>, param: R)
}

