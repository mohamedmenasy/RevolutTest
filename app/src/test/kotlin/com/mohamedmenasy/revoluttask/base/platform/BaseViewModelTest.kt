package com.mohamedmenasy.revoluttask.base.platform

import androidx.lifecycle.MutableLiveData
import com.mohamedmenasy.core.exception.Failure
import com.mohamedmenasy.core.exception.Failure.NetworkConnection
import com.mohamedmenasy.revoluttask.AndroidTest
import org.amshove.kluent.shouldBeInstanceOf
import org.junit.Test

class BaseViewModelTest : AndroidTest() {

  @Test
  fun `should handle failure by updating live data`() {
    val viewModel = MyViewModel()

    viewModel.handleError(NetworkConnection)

    val failure = viewModel.failure
    val error = viewModel.failure.value

    failure shouldBeInstanceOf MutableLiveData::class.java
    error shouldBeInstanceOf NetworkConnection::class.java
  }

  private class MyViewModel : BaseViewModel() {
    fun handleError(failure: Failure) = handleFailure(failure)
  }
}