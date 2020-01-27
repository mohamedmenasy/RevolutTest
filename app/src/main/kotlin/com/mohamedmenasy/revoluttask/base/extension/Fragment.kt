package com.mohamedmenasy.revoluttask.base.extension

import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.Factory
import androidx.lifecycle.ViewModelProviders
import com.mohamedmenasy.revoluttask.base.platform.BaseActivity
import com.mohamedmenasy.revoluttask.base.platform.BaseFragment
import kotlinx.android.synthetic.main.activity_layout.*

/**
 * Extension class for the [FragmentManager]
 */
inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction) =
    beginTransaction().func().commit()

inline fun <reified T : ViewModel> Fragment.viewModel(
    factory: Factory,
    body: T.() -> Unit
): T {
  val vm = ViewModelProviders.of(this, factory)[T::class.java]
  vm.body()
  return vm
}

val BaseFragment.viewContainer: View get() = (activity as BaseActivity).fragmentContainer

