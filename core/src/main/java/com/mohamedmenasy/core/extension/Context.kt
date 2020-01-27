package com.mohamedmenasy.core.extension

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

//get network info
val Context.networkInfo: NetworkInfo?
  get() =
    (this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).activeNetworkInfo
