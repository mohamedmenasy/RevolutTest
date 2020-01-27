package com.mohamedmenasy.core.extension

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.mohamedmenasy.core.exception.Failure

/**
 * Observe the live data for any updates also observe for any failure
 * @receiver LifecycleOwner
 * @param liveData
 * @param body
 */
fun <T : Any, L : LiveData<T>> LifecycleOwner.observe(
    liveData: L,
    body: (T?) -> Unit
) =
    liveData.observe(this, Observer(body))

fun <L : LiveData<Failure>> LifecycleOwner.failure(
    liveData: L,
    body: (Failure?) -> Unit
) =
    liveData.observe(this, Observer(body))