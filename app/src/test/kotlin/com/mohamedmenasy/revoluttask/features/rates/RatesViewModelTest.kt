package com.mohamedmenasy.revoluttask.features.rates

import com.mohamedmenasy.revoluttask.AndroidTest
import com.mohamedmenasy.revoluttask.features.rates.usecase.GetRates
import com.mohamedmenasy.revoluttask.features.rates.viewmodel.RatesViewModel
import com.nhaarman.mockito_kotlin.anyVararg
import com.nhaarman.mockito_kotlin.eq
import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.verify
import org.amshove.kluent.shouldEqualTo
import org.junit.Before
import org.junit.Test
import org.mockito.Mock

class RatesViewModelTest : AndroidTest() {
  companion object {
    const val BASE = "EUR"
  }


  private lateinit var ratesViewModel: RatesViewModel
  @Mock
  private lateinit var getRates: GetRates

  @Before
  fun setUp() {
    ratesViewModel = RatesViewModel(getRates)
    given {
      (getRates).execute(
          anyVararg(), anyVararg(), eq(GetRates.Params(BASE))
      )
    }.willReturn(true)
  }

  @Test
  fun `should dispose view and useCase`() {
    ratesViewModel.destroy()
    verify(getRates).dispose()
  }

  @Test
  fun `should load rates data and update live data`() {
    ratesViewModel.loadRates(BASE)

    getRates.execute(
        anyVararg(), anyVararg(), eq(GetRates.Params(BASE))
    )

    ratesViewModel.ratesData.observeForever {
      it.first().amount shouldEqualTo 0.0
      it.first().currency shouldEqualTo ""
    }
  }
}
