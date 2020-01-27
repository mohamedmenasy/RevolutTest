package com.mohamedmenasy.revoluttask.base.di.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mohamedmenasy.revoluttask.features.rates.viewmodel.RatesViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * ViewModel Module for Dagger
 */
@Module
abstract class ViewModelModule {
  @Binds
  internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

  @Binds
  @IntoMap
  @ViewModelKey(RatesViewModel::class)
  abstract fun bindsRatesViewModel(ratesViewModel: RatesViewModel): ViewModel
}