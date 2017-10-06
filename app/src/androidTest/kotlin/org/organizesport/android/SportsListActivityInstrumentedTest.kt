package org.organizesport.android

import android.support.test.espresso.Espresso.closeSoftKeyboard
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.typeText
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.RootMatchers
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import org.hamcrest.CoreMatchers

import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.organizesport.android.view.activities.SportsListActivity
import android.support.test.espresso.Espresso.onData
import android.support.test.espresso.DataInteraction
import org.hamcrest.CoreMatchers.anything


/**
 * This class relates to the instrumentation tests of the 'SportsListActivityInstrumentedTest' View
 * (UI tests).
 *
 * @author psor1i
 * @since 1.0
 */
@RunWith(AndroidJUnit4::class)
class SportsListActivityInstrumentedTest {

    @Rule
    @JvmField
    val activityTestRule = ActivityTestRule<SportsListActivity>(SportsListActivity::class.java)

    @Test
    fun testSportsListIsShown() {
//        onView(withId(R.id.lv_splist_activity_sports_list))
//                .inRoot(RootMatchers.withDecorView(CoreMatchers.not(CoreMatchers.`is`(activityTestRule.activity.window.decorView))))
//                .check(matches(isDisplayed()))
    }

    @Test
    fun testSportsListContainsData() {

        val sport = "athletics"

//        onView(withId(R.id.lv_splist_activity_sports_list))
//                .inRoot(RootMatchers.withDecorView(CoreMatchers.not(CoreMatchers.`is`(activityTestRule.activity.window.decorView))))
//                .check(matches(isDisplayed()))

//        assert(onData(anything())
//                .inAdapterView(withId(R.id.lv_splist_activity_sports_list))
//                .atPosition(0).equals(sport))
    }
}
