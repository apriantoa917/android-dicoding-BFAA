package com.aprianto.mygithub.ui.setting


import android.view.View
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.isRoot
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.aprianto.mygithub.R
import org.hamcrest.Matcher
import org.junit.Before
import org.junit.Test


class SettingsActivityTest {

    // buka activity
    @Before
    fun setup() {
        ActivityScenario.launch(SettingsActivity::class.java)
    }

    // function untuk set jeda
    private fun waitFor(delay: Long = 3000): ViewAction {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View> = isRoot()
            override fun getDescription(): String = "wait for $delay milliseconds"
            override fun perform(uiController: UiController, v: View?) {
                uiController.loopMainThreadForAtLeast(delay)
            }
        }
    }

    // test menggunakan tema auto
    @Test
    fun testTema() {
        // tunggu jeda untuk load default theme dari datastore
        onView(isRoot()).perform(waitFor(5000))

        // perform tema light
        onView(withId(R.id.theme_light)).perform(click())
        onView(isRoot()).perform(waitFor())

        // perform tema auto
        onView(withId(R.id.theme_auto)).perform(click())
        onView(isRoot()).perform(waitFor())

        // perform tema dark
        onView(withId(R.id.theme_dark)).perform(click())
        onView(isRoot()).perform(waitFor())
    }
}