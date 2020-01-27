package com.mohamedmenasy.revoluttask.features.rates.data.remote.response


import com.google.gson.annotations.SerializedName
import com.mohamedmenasy.revoluttask.features.rates.data.RateData

data class CurrenciesEntity(
    @SerializedName("base")
    val base: String,
    @SerializedName("date")
    val date: String,
    @SerializedName("rates")
    val rates: Map<String, Double>
) {
  fun getRatesDataList(): List<RateData> {
    val rateDataList = arrayListOf<RateData>()
    rates.forEach { rateDataList.add(RateData(it.key, "", "", it.value)) }
    return rateDataList
  }
}