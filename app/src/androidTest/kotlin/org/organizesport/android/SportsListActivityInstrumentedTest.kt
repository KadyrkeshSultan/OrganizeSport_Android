package org.organizesport.android

import android.support.test.espresso.Espresso.onData
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.RootMatchers
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import org.hamcrest.CoreMatchers
import org.hamcrest.Matchers.anything

import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.organizesport.android.view.activities.SportsListActivity

/**
 * This class relates to the instrumentation tests of the 'SportsListActivity' View
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

        // TODO: need to be implemented
//        onView(withId(R.id.lv_splist_activity_sports_list))
//                .inRoot(RootMatchers.withDecorView(CoreMatchers.not(CoreMatchers.`is`(activityTestRule.activity.window.decorView))))
//                .check(matches(isDisplayed()))
    }

    @Test
    fun testSportsListContainsData() {

        val sport = "athletics"

        // TODO: need to be implemented
//        onView(withId(R.id.lv_splist_activity_sports_list))
//                .inRoot(RootMatchers.withDecorView(CoreMatchers.not(CoreMatchers.`is`(activityTestRule.activity.window.decorView))))
//                .check(matches(isDisplayed()))

//        assert(onData(anything())
//                .inAdapterView(withId(R.id.lv_splist_activity_sports_list))
//                .atPosition(0).equals(sport))
    }
}
