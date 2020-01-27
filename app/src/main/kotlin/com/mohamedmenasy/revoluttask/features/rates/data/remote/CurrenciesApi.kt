package com.mohamedmenasy.revoluttask.features.rates.data.remote

import com.mohamedmenasy.revoluttask.features.rates.data.remote.response.CurrenciesEntity
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Retrofit class for currencies API
 */
internal interface CurrenciesApi {
  companion object {
    //Parameters
    const val BASE = "base"

    //Api Urls
    const val LATEST = "latest"
  }

  @GET(LATEST)
  fun getRates(
      @Query(
          BASE
      ) base: String
  ): Single<CurrenciesEntity>
}
