package com.base.application.kotlin.view.splash

import android.support.test.filters.LargeTest
import org.junit.Rule
import org.junit.Test
import android.support.test.espresso.intent.Intents
import android.support.test.espresso.intent.Intents.intended
import android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent
import org.junit.After
import org.junit.Before
import android.support.test.espresso.Espresso
import android.support.test.rule.ActivityTestRule
import com.base.application.kotlin.utils.ElapsedTimeIdlingResource
import com.base.application.kotlin.view.main.MainActivity


@LargeTest
class SplashActivityTest{

    @Rule
    @JvmField
    var splashActivityRule = ActivityTestRule(SplashActivity::class.java, true, false)

    var splashWaitingTimeMs = 3100


    @Before
    @Throws(Exception::class)
    fun setUp() {
        Intents.init()
    }


    @After
    @Throws(Exception::class)
    fun tearDown() {
        Intents.release()
    }

    @Test
    @Throws(InterruptedException::class)
    fun viewSplash_NavigateToMainActivityAfter3000ms()  {
        splashActivityRule.launchActivity(null)

        val idlingResource = ElapsedTimeIdlingResource(splashWaitingTimeMs)
        Espresso.registerIdlingResources(idlingResource)

        intended(hasComponent(MainActivity::class.java.getName()))

        Espresso.unregisterIdlingResources(idlingResource)
    }
}