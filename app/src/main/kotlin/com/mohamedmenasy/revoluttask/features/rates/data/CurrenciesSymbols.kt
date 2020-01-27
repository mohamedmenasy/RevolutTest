package com.mohamedmenasy.revoluttask.features.rates.data

val currenciesSymbolsMap = mapOf(
    "AUD" to CurrenciesStaticData("Australian dollar", "https://restcountries.eu/data/ata.svg"),
    "BGN" to CurrenciesStaticData("Bulgarian lev", "https://restcountries.eu/data/bgr.svg"),
    "BRL" to CurrenciesStaticData("Brazilian real", "https://restcountries.eu/data/bra.svg"),
    "CAD" to CurrenciesStaticData("Canadian dollar", "https://restcountries.eu/data/can.svg"),
    "CHF" to CurrenciesStaticData("Swiss franc", "https://restcountries.eu/data/lie.svg"),
    "CNY" to CurrenciesStaticData("Chinese yuan", "https://restcountries.eu/data/chn.svg"),
    "CZK" to CurrenciesStaticData("Czech koruna", "https://restcountries.eu/data/cze.svg"),
    "DKK" to CurrenciesStaticData("Danish krone", "https://restcountries.eu/data/dnk.svg"),
    "EUR" to CurrenciesStaticData("Euro", "https://upload.wikimedia.org/wikipedia/commons/b/b7/Flag_of_Europe.svg"),
    "GBP" to CurrenciesStaticData("Australian dollar", "https://restcountries.eu/data/ata.svg"),
    "HKD" to CurrenciesStaticData("Hong Kong dollar", "https://restcountries.eu/data/hkg.svg"),
    "HRK" to CurrenciesStaticData("Croatian kuna", "https://restcountries.eu/data/hrv.svg"),
    "HUF" to CurrenciesStaticData("Hungarian forint", "https://restcountries.eu/data/hun.svg"),
    "IDR" to CurrenciesStaticData("Indonesian rupiah", "https://restcountries.eu/data/idn.svg"),
    "ILS" to CurrenciesStaticData("Israeli new shekel", "https://restcountries.eu/data/isr.svg"),
    "INR" to CurrenciesStaticData("Bhutanese ngultrum", "https://restcountries.eu/data/btn.svg"),
    "ISK" to CurrenciesStaticData("Icelandic króna", "https://restcountries.eu/data/isl.svg"),
    "JPY" to CurrenciesStaticData("Japanese yen", "https://restcountries.eu/data/jpn.svg"),
    "KRW" to CurrenciesStaticData("South Korean won", "https://restcountries.eu/data/kor.svg"),
    "MXN" to CurrenciesStaticData("Mexican peso", "https://restcountries.eu/data/mex.svg"),
    "MYR" to CurrenciesStaticData("Malaysian ringgit", "https://restcountries.eu/data/mys.svg"),
    "NOK" to CurrenciesStaticData("Norwegian krone", "https://restcountries.eu/data/bvt.svg"),
    "NZD" to CurrenciesStaticData("Cook Islands dollar", "https://restcountries.eu/data/cok.svg"),
    "PHP" to CurrenciesStaticData("Philippine peso", "https://restcountries.eu/data/phl.svg"),
    "PLN" to CurrenciesStaticData("Polish złoty", "https://restcountries.eu/data/pol.svg"),
    "RON" to CurrenciesStaticData("Romanian leu", "https://restcountries.eu/data/rou.svg"),
    "RUB" to CurrenciesStaticData("Russian ruble", "https://restcountries.eu/data/rus.svg"),
    "SEK" to CurrenciesStaticData("Swedish krona", "https://restcountries.eu/data/swe.svg"),
    "SGD" to CurrenciesStaticData("Singapore dollar", "https://restcountries.eu/data/brn.svg"),
    "THB" to CurrenciesStaticData("Thai baht", "https://restcountries.eu/data/tha.svg"),
    "TRY" to CurrenciesStaticData("Turkish lira", "https://restcountries.eu/data/tur.svg"),
    "USD" to CurrenciesStaticData("United State Dollar", "https://restcountries.eu/data/asm.svg"),
    "ZAR" to CurrenciesStaticData("South African rand", "https://restcountries.eu/data/lso.svg")
)

fun fillCurrencyData(list: List<RateData>): List<RateData> {
  val returnData = arrayListOf<RateData>()
  list.forEach { rateData ->
    val currency = rateData.currency
    val currencyDescription = currenciesSymbolsMap[rateData.currency]?.currencyDescription
    val icon = currenciesSymbolsMap[rateData.currency]?.flagUrl
    val amount = rateData.amount
    returnData.add(RateData(currency, currencyDescription!!, icon!!, amount))
  }
  return returnData
}
