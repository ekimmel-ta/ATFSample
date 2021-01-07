package com.example.atfsample

import androidx.test.espresso.Espresso
import androidx.test.espresso.accessibility.AccessibilityChecks
import androidx.test.espresso.action.ViewActions
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import org.hamcrest.BaseMatcher
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(RobolectricTestRunner::class)
@LargeTest
@Config(sdk = [23])
class ExampleUnitTest {

    @Rule
    @JvmField
    var activityTestRule = ActivityTestRule(MainActivity::class.java)

    @Before
    fun setup() {
        AccessibilityChecks.enable()
            .setRunChecksFromRootView(true)
    }


    @Test
    fun testAccessibility() {
        // For accessibility checks to be done, a ViewAction needs to be performed,
        // so for that we're just doing a click on first element on the screen
        Espresso.onView(firstOfAnyElement()).perform(ViewActions.click())
    }

    private fun <T> firstOfAnyElement(): Matcher<T>? {
        return object : BaseMatcher<T>() {
            var isFirst = true

            override fun matches(item: Any?): Boolean {
                if (isFirst) {
                    isFirst = false
                    return true
                }
                return false
            }

            override fun describeTo(description: Description) {
                description.appendText("should return first matching item")
            }
        }
    }
}