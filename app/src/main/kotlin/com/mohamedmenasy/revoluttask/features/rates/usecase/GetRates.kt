package com.mohamedmenasy.revoluttask.features.rates.usecase

import com.mohamedmenasy.core.executor.ExecutionScheduler
import com.mohamedmenasy.core.interactor.UseCase
import com.mohamedmenasy.revoluttask.features.rates.data.CurrenciesRepository
import com.mohamedmenasy.revoluttask.features.rates.data.RateData
import com.mohamedmenasy.revoluttask.features.rates.usecase.GetRates.Params
import io.reactivex.Single
import javax.inject.Inject

/**
 * [UseCase] class to get the rates data
 */
class GetRates
@Inject constructor(
    private val currenciesRepository: CurrenciesRepository,
    private val scheduler: ExecutionScheduler
) : UseCase.RxSingle<List<RateData>, Params>() {
  override fun build(params: Params?): Single<List<RateData>> =
      currenciesRepository.rates(params!!.base)
          .map { it }
          .compose(scheduler.highPrioritySingle())

  data class Params(val base: String)
}
