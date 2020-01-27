package com.mohamedmenasy.revoluttask.features.rates.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mohamedmenasy.core.extension.loadFromUrl
import com.mohamedmenasy.revoluttask.R
import com.mohamedmenasy.revoluttask.features.rates.data.RateData
import com.mohamedmenasy.revoluttask.features.rates.data.currenciesSymbolsMap
import kotlinx.android.synthetic.main.adapter_item_rate.view.*

/**
 * The main [RatesAdapter] to handle the rates data
 */
class RatesAdapter(private var ratesData: MutableList<RateData>) : RecyclerView.Adapter<RatesAdapter.ViewHolder>() {

  companion object {
    const val topPosition = 0
    const val defaultCurrency = "EUR"
    const val defaultAmount = 1.0
  }

  private var base: String = defaultCurrency
  private var amount: Double? = defaultAmount

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val view = LayoutInflater.from(parent.context)
        .inflate(R.layout.adapter_item_rate, parent, false)
    return ViewHolder(view)
  }

  override fun getItemCount() = ratesData.size

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    holder.bind(ratesData[position])
  }

  fun setData(newData: MutableList<RateData>) {
    //Add Euro to be the fist element to the list
    if (newData.find { it.currency == defaultCurrency } == null) {
      val euroData = currenciesSymbolsMap[defaultCurrency]
      euroData?.let {
        newData.add(0, RateData(defaultCurrency,
            it.currencyDescription,
            it.flagUrl,
            1.0))
      }
    }

    if (ratesData.isNullOrEmpty()) {
      val diffCallback = RatesDiffCallback(ratesData, newData)
      val diffResult = DiffUtil.calculateDiff(diffCallback)
      ratesData.clear()
      ratesData.addAll(newData)
      diffResult.dispatchUpdatesTo(this)
    } else {
      ratesData.forEachIndexed { index, rateData ->

        val newData = newData.find { it.currency == rateData.currency }
        if (newData != null) {
          ratesData[index] = newData
        }
      }
    }
    notifyItemRangeChanged(topPosition + 1, ratesData.size)
    //notifyDataSetChanged()
  }

  //swap item to the top of the recycleView
  fun swapItem(fromPosition: Int) {
    val item = ratesData.removeAt(fromPosition)
    ratesData.add(topPosition, item)
    notifyItemMoved(fromPosition, topPosition)
  }

  fun getBase() = base

  inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(rateData: RateData) = with(itemView) {

      currencyTV.text = rateData.currency
      currencyIV.loadFromUrl(rateData.icon)
      descriptionTV.text = rateData.currencyDescription

      rateET.setOnFocusChangeListener { view, hasFocus ->
        if (hasFocus && adapterPosition != topPosition) {
          itemView.performClick()
        }
      }

      if (adapterPosition != topPosition) {
        val index = ratesData[adapterPosition].amount
        if (amount != null) {
          val newAmount = index * amount!!
          val stringAmount = String.format("%.3f", newAmount)
          if (!rateET.hasFocus()) {
            rateET.text?.replace(
                0,
                rateET.text!!.length,
                stringAmount
            )
          }
        }

      } else {
        val stringAmount = String.format("%.3f", amount)
        if (!rateET.hasFocus()) {
          rateET.text?.replace(0, rateET.text!!.length, stringAmount)
        }
      }

      itemView.setOnClickListener {
        rateET.requestFocus()
        rateET.setSelection(rateET.text.toString().length)
        if (!(adapterPosition == topPosition || adapterPosition < topPosition)) {
          base = ratesData[adapterPosition].currency
          amount = rateET.text.toString().toDoubleOrNull()
          swapItem(adapterPosition)
        }
      }

      rateET.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(p0: Editable?) {
          val stringAmount = p0.toString()
          if (adapterPosition == topPosition && amount != stringAmount.toDoubleOrNull()) {
            amount = stringAmount.toDoubleOrNull()
          }
        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

      })

    }
  }

  //DiffUtil Callbacks
  class RatesDiffCallback(private val oldList: List<RateData>, private val newList: List<RateData>) : DiffUtil.Callback() {
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition] == newList[newItemPosition]

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        newList[newItemPosition] == oldList[oldItemPosition]

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
      val oldData = oldList[oldItemPosition]
      val newData = newList[newItemPosition]

      val diff = Bundle()

      if (oldData.amount != newData.amount) {
        diff.putDouble("amount", newData.amount)
      }
      if (diff.size() == 0) {
        return null
      }
      return diff

    }
  }
}
