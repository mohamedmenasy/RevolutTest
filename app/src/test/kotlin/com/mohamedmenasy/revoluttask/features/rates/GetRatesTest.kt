package com.mohamedmenasy.revoluttask.features.rates

import com.mohamedmenasy.revoluttask.TestScheduler
import com.mohamedmenasy.revoluttask.TestScheduler.Function.HighPrioritySingle
import com.mohamedmenasy.revoluttask.UnitTest
import com.mohamedmenasy.revoluttask.features.rates.data.CurrenciesRepository
import com.mohamedmenasy.revoluttask.features.rates.data.RateData
import com.mohamedmenasy.revoluttask.features.rates.usecase.GetRates
import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyNoMoreInteractions
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mock

class GetRatesTest : UnitTest() {
  companion object {
    const val BASE = "EUR"
  }

  private lateinit var getRates: GetRates

  @Mock
  private lateinit var currenciesRepository: CurrenciesRepository

  private val testScheduler = TestScheduler()

  @Before
  fun setUp() {
    getRates = GetRates(currenciesRepository, testScheduler)
    given { currenciesRepository.rates(BASE) }.willReturn(
        Single.create<List<RateData>> {})
  }

  @Test
  fun `should get rates data from repository`() {
    getRates.build(GetRates.Params(BASE))

    verify(currenciesRepository).rates(BASE)
    verifyNoMoreInteractions(currenciesRepository)

    testScheduler verify HighPrioritySingle
  }

}