package com.mohamedmenasy.revoluttask.base.navigation

import android.content.Context
import com.mohamedmenasy.revoluttask.features.rates.view.RatesActivity
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Navigator class is responsible to navigate between the activities
 */
@Singleton
class Navigator
@Inject constructor() {

  fun showMain(context: Context) {
    showRatesActivity(context)
  }

  //show the rates activity by calling the callingIntent method
  private fun showRatesActivity(context: Context) =
      context.startActivity(RatesActivity.callingIntent(context))
}


