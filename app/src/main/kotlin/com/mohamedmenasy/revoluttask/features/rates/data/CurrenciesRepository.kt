package com.mohamedmenasy.revoluttask.features.rates.data

import com.mohamedmenasy.revoluttask.features.rates.data.remote.CurrenciesService
import io.reactivex.Single
import javax.inject.Inject

/**
 * Repository class which contains network and disk implementations
 */
interface CurrenciesRepository {
  fun rates(base: String): Single<List<RateData>>

  class Network
  @Inject constructor(
      private val service: CurrenciesService
  ) : CurrenciesRepository {
    override fun rates(base: String): Single<List<RateData>> =
        service.getRates(
            base
        ).map { entity -> entity.getRatesDataList() }
  }

  class Disk
  @Inject constructor(
      private val service: CurrenciesService
  ) : CurrenciesRepository {
    override fun rates(base: String) = TODO()
  }

}