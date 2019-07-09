package com.edbutch.materialcountries

import android.app.SearchableInfo
import androidx.test.InstrumentationRegistry
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule
import androidx.test.espresso.action.ViewActions.typeText
import android.widget.AutoCompleteTextView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewAction
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import java.net.URLEncoder


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @Rule
    @JvmField
    val rule : ActivityTestRule<SearchActivity> = ActivityTestRule(SearchActivity::class.java)


    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("com.edbutch.materialcountries", appContext.packageName)
    }

    @Test
    fun user_can_search_main_activity(){
        onView(withId(R.id.searchText)).perform(typeText("Hello World"))
        onView(withId(R.id.searchButton)).perform(click())
        onView(withId(R.id.searchText)).check(matches(withText("Hello%20World")))

    }

}
