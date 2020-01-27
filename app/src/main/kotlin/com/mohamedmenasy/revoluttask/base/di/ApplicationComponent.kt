package com.mohamedmenasy.revoluttask.base.di

import com.mohamedmenasy.revoluttask.AndroidApplication
import com.mohamedmenasy.revoluttask.base.di.viewmodel.ViewModelModule
import com.mohamedmenasy.revoluttask.base.navigation.RouteActivity
import com.mohamedmenasy.revoluttask.features.rates.view.RatesFragment
import dagger.Component
import javax.inject.Singleton

/**
 * Application Component for Dagger
 */
@Singleton
@Component(modules = [ApplicationModule::class, ViewModelModule::class])
interface ApplicationComponent {
  fun inject(application: AndroidApplication)
  fun inject(routeActivity: RouteActivity)
  fun inject(ratesFragment: RatesFragment)
}
