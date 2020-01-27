package com.mohamedmenasy.revoluttask.features.rates.viewmodel

import androidx.lifecycle.MutableLiveData
import com.mohamedmenasy.core.exception.Failure
import com.mohamedmenasy.revoluttask.base.platform.BaseViewModel
import com.mohamedmenasy.revoluttask.features.rates.data.RateData
import com.mohamedmenasy.revoluttask.features.rates.data.fillCurrencyData
import com.mohamedmenasy.revoluttask.features.rates.usecase.GetRates
import com.mohamedmenasy.revoluttask.features.rates.usecase.GetRates.Params
import javax.inject.Inject

/**
 * [RatesViewModel] class to get rates data and update the [MutableLiveData]
 */
class RatesViewModel
@Inject constructor(private val getRates: GetRates) : BaseViewModel() {

  var ratesData: MutableLiveData<List<RateData>> = MutableLiveData()

  fun loadRates(base: String) {
    getRates.execute(
        {
          ratesData.value = fillCurrencyData(it)
        }, {
      handleFailure(Failure.ServerError)
    }, Params(base)
    )
  }

  override fun onCleared() {
    super.onCleared()
    destroy()
  }

  fun destroy() {
    getRates.dispose()
  }
}