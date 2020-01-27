package com.mohamedmenasy.revoluttask.base.navigation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mohamedmenasy.revoluttask.AndroidApplication
import com.mohamedmenasy.revoluttask.base.di.ApplicationComponent
import javax.inject.Inject

/**
 * The first activity triggered by the android system and route to the specified activity by using navigator
 */
class RouteActivity : AppCompatActivity() {

  private val appComponent: ApplicationComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
    (application as AndroidApplication).appComponent
  }

  @Inject
  internal lateinit var navigator: Navigator

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    appComponent.inject(this)
    navigator.showMain(this)
  }
}
