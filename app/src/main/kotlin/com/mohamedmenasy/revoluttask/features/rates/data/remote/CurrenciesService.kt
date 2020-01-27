package com.mohamedmenasy.revoluttask.features.rates.data.remote

import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

/**
 * services class which implementation of the [CurrenciesApi]
 */
@Singleton
class CurrenciesService
@Inject constructor(retrofit: Retrofit) : CurrenciesApi {
  private val ratesApi by lazy {
    retrofit.create(
        CurrenciesApi::class.java
    )
  }

  override fun getRates(base: String) =
      ratesApi.getRates(base)
}