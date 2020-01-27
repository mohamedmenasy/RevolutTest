package com.mohamedmenasy.revoluttask.base.navigation

import com.mohamedmenasy.revoluttask.AndroidTest
import com.mohamedmenasy.revoluttask.features.rates.view.RatesActivity
import com.mohamedmenasy.revoluttask.shouldNavigateTo
import org.junit.Before
import org.junit.Test

class NavigatorTest : AndroidTest() {

  private lateinit var navigator: Navigator

  @Before
  fun setup() {
    navigator = Navigator()
  }

  @Test
  fun `should forward user to main screen`() {

    navigator.showMain(activityContext())

    RouteActivity::class shouldNavigateTo RatesActivity::class
  }
}
