package com.mohamedmenasy.revoluttask.features.rates.view

import android.content.Context
import android.content.Intent
import com.mohamedmenasy.revoluttask.base.platform.BaseActivity

/**
 * The main [RatesActivity] for currencies rates
 */
class RatesActivity : BaseActivity() {

  companion object {
    fun callingIntent(context: Context) = Intent(context, RatesActivity::class.java)
  }

  override fun fragment() = RatesFragment()

}
