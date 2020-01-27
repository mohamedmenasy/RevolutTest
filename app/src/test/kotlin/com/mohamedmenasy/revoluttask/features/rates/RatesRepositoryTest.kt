package com.mohamedmenasy.revoluttask.features.rates

import com.mohamedmenasy.revoluttask.UnitTest
import com.mohamedmenasy.revoluttask.features.rates.data.CurrenciesRepository.Network
import com.mohamedmenasy.revoluttask.features.rates.data.remote.CurrenciesService
import com.mohamedmenasy.revoluttask.features.rates.data.remote.response.CurrenciesEntity
import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.verify
import io.reactivex.Single
import org.amshove.kluent.shouldEqual
import org.junit.Before
import org.junit.Test
import org.mockito.Mock

class RatesRepositoryTest : UnitTest() {
  companion object {
    const val BASE = "EUR"
    const val TEST_CURRENCY = "EUR"
    const val TEST_CURRENCY_VALUE = 1.6191

  }

  private lateinit var networkRepository: Network
  @Mock
  private lateinit var service: CurrenciesService

  @Before
  fun setUp() {
    networkRepository = Network(service)
    given { service.getRates(BASE) }.willReturn(
        Single.just(
            CurrenciesEntity("EUR", "2018-09-06", mapOf("EUR" to 1.6191))
        )
    )
  }

  @Test
  fun `should get rates data list from service`() {
    val testObserver = networkRepository.rates(BASE)
        .test()
    val currencyData = testObserver.values()[0][0]
    with(currencyData) {
      currency shouldEqual TEST_CURRENCY
      amount shouldEqual TEST_CURRENCY_VALUE
    }

    testObserver.assertValueCount(1)
    testObserver.assertComplete()
    verify(service).getRates(BASE)
  }

}