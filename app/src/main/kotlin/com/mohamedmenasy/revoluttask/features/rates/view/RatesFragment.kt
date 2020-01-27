package com.mohamedmenasy.revoluttask.features.rates.view

import android.os.Bundle
import android.view.View
import androidx.annotation.StringRes
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SimpleItemAnimator
import com.mohamedmenasy.core.exception.Failure
import com.mohamedmenasy.core.extension.failure
import com.mohamedmenasy.core.extension.observe
import com.mohamedmenasy.revoluttask.R
import com.mohamedmenasy.revoluttask.base.extension.viewModel
import com.mohamedmenasy.revoluttask.base.platform.BaseFragment
import com.mohamedmenasy.revoluttask.features.rates.data.RateData
import com.mohamedmenasy.revoluttask.features.rates.viewmodel.RatesViewModel
import kotlinx.android.synthetic.main.fragment_rates.*
import java.util.concurrent.TimeUnit
import kotlin.concurrent.fixedRateTimer

/**
 * The main [RatesFragment] to display the list of rates
 */
class RatesFragment : BaseFragment() {
  private lateinit var ratesViewModel: RatesViewModel
  override fun layoutId() = R.layout.fragment_rates
  var ratesAdapter = RatesAdapter(mutableListOf())

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    appComponent.inject(this)
    retainInstance = true

    ratesViewModel =
        viewModel(viewModelFactory) {
          observe(ratesData, ::renderRateData)
          failure(failure, ::handleFailure)
        }
  }

  //Pass the list of rates to adapter
  private fun renderRateData(list: List<RateData>?) {
    list?.let { ratesAdapter.setData(list.toMutableList()) }
  }

  override fun onViewCreated(
      view: View,
      savedInstanceState: Bundle?
  ) {
    super.onViewCreated(view, savedInstanceState)

    ratesRecyclerView.apply {
      layoutManager = LinearLayoutManager(activity)
      adapter = ratesAdapter
      setHasFixedSize(true)
      (itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false
    }
    ratesAdapter.registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {
      override fun onItemRangeMoved(fromPosition: Int, toPosition: Int, itemCount: Int) {
        super.onItemRangeMoved(fromPosition, toPosition, itemCount)
        ratesRecyclerView.scrollToPosition(RatesAdapter.topPosition)
      }
    })

    loadData()
  }

  //load rates each 1 second
  private fun loadData() {
    fixedRateTimer("timer", false, 0, TimeUnit.SECONDS.toMillis(1)) {
      ratesViewModel.loadRates(ratesAdapter.getBase())
    }
  }

  //display error message
  private fun handleFailure(failure: Failure?) {
    when (failure) {
      is Failure.NetworkConnection -> renderFailure(R.string.failure_network_connection)
      is Failure.ServerError -> renderFailure(R.string.failure_server_error)
    }
  }

  //display error message
  private fun renderFailure(@StringRes message: Int) {
    notify(message)
  }
}
