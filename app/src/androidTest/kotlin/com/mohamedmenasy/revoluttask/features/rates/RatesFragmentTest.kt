package com.mohamedmenasy.revoluttask.features.rates

import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.mohamedmenasy.revoluttask.R.id
import com.mohamedmenasy.revoluttask.features.rates.view.RatesActivity
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class RatesFragmentTest {

  //TODO : Add more tests

  @get:Rule
  var mActivityTestRule = ActivityTestRule(RatesActivity::class.java)

  /**
   * Check that the fragment is displayed
   */
  @Test
  fun checkControlsAreDisplayed() {
    onView(
        Matchers.allOf(withId(id.containerLayout),
            childAtPosition(
                Matchers.allOf(withId(id.ratesRecyclerView),
                    childAtPosition(
                        withId(id.container),
                        0)),
                6),
            isDisplayed()))

  }

  private fun childAtPosition(
      parentMatcher: Matcher<View>, position: Int): Matcher<View> {

    return object : TypeSafeMatcher<View>() {
      override fun describeTo(description: Description) {
        description.appendText("Child at position $position in parent ")
        parentMatcher.describeTo(description)
      }

      public override fun matchesSafely(view: View): Boolean {
        val parent = view.parent
        return parent is ViewGroup && parentMatcher.matches(parent)
            && view == parent.getChildAt(position)
      }
    }
  }
}
