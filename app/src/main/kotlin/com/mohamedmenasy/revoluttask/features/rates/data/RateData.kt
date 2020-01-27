package com.mohamedmenasy.revoluttask.features.rates.data

/**
 * data class holds the currency data for the view usage
 */
data class RateData(
    val currency: String = "",
    val currencyDescription: String = "",
    val icon: String = "",
    val amount: Double = 0.0
) {
  override fun equals(other: Any?): Boolean {
    if (javaClass != other?.javaClass) {
      return false
    }

    other as RateData

    if (currency != other.currency) {
      return false
    }

    if (currencyDescription != other.currencyDescription) {
      return false
    }
    if (icon != other.icon) {
      return false
    }
    if (amount != other.amount) {
      return false
    }

    return true
  }

  override fun hashCode(): Int {
    return currency.hashCode() * currencyDescription.hashCode() * amount.hashCode()
  }
}