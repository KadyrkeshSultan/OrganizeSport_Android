package org.organizesport.android

import android.support.test.espresso.Espresso.closeSoftKeyboard
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.typeText
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4

import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.organizesport.android.R
import org.organizesport.android.view.activities.LoginActivity

/**
 * This class relates to the instrumentation tests of the 'LoginActivity' View (UI tests).
 *
 * @author psor1i
 * @since 1.0
 */
@RunWith(AndroidJUnit4::class)
class LoginActivityInstrumentedTest {

    @Rule
    @JvmField
    val activity = ActivityTestRule<LoginActivity>(LoginActivity::class.java)

    @Test
    fun testEditTextInsertion() {
        val expectedEmail = "pablo.sordomartinez@unitedcuisines.de"
        val expectedPassword = "pablo.sordomartinez"

        onView(withId(R.id.et_email_activity_login)).perform(typeText(expectedEmail))
        closeSoftKeyboard()
        onView(withId(R.id.et_password_activity_login)).perform(typeText(expectedPassword))
        closeSoftKeyboard()

        onView(withId(R.id.et_email_activity_login)).check(matches(withText(expectedEmail)))
        onView(withId(R.id.et_password_activity_login)).check(matches(withText(expectedPassword)))
    }

    @Test
    fun testAccessModeStatusToggle() {
        onView(withId(R.id.tb_access_mode_status_activity_login)).perform(click())

        onView(withId(R.id.tv_title_activity_login)).check(matches(withText("Register")))
        onView(withId(R.id.btn_login_activity_login)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.INVISIBLE)))
        onView(withId(R.id.btn_register_activity_login)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
    }
}