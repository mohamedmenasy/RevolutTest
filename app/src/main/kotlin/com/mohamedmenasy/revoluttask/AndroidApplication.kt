package com.mohamedmenasy.revoluttask

import android.app.Application
import com.mohamedmenasy.revoluttask.base.di.ApplicationComponent
import com.mohamedmenasy.revoluttask.base.di.ApplicationModule
import com.mohamedmenasy.revoluttask.base.di.DaggerApplicationComponent
import com.squareup.leakcanary.LeakCanary

/**
 * Application Class
 */

class AndroidApplication : Application() {

  val appComponent: ApplicationComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
    DaggerApplicationComponent
        .builder()
        .applicationModule(ApplicationModule(this))
        .build()
  }

  override fun onCreate() {
    super.onCreate()
    this.injectMembers()
    this.initializeLeakDetection()
  }

  //inject the dagger members
  private fun injectMembers() = appComponent.inject(this)

  //initialize LeakCanary to detect memory leaks
  private fun initializeLeakDetection() {
    if (BuildConfig.DEBUG) LeakCanary.install(this)
  }
}
