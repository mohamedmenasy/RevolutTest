package com.mohamedmenasy.revoluttask.base.platform

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mohamedmenasy.core.exception.Failure

/**
 * Base ViewModel class with default Failure handling.
 * @see ViewModel
 * @see Failure
 */
abstract class BaseViewModel : ViewModel() {

  var failure: MutableLiveData<Failure> = MutableLiveData()

  protected fun handleFailure(failure: Failure) {
    this.failure.value = failure
  }
}