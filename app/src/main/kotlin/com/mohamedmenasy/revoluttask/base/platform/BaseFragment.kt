package com.mohamedmenasy.revoluttask.base.platform

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.mohamedmenasy.revoluttask.AndroidApplication
import com.mohamedmenasy.revoluttask.base.di.ApplicationComponent
import com.mohamedmenasy.revoluttask.base.extension.viewContainer
import javax.inject.Inject

/**
 * Base Fragment class with helper methods for handling views and back button events.
 *
 * @see Fragment
 */
abstract class BaseFragment : Fragment() {

  abstract fun layoutId(): Int

  val appComponent: ApplicationComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
    (activity?.application as AndroidApplication).appComponent
  }

  @Inject
  lateinit var viewModelFactory: ViewModelProvider.Factory

  override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?
  ): View =
      inflater.inflate(layoutId(), container, false)

  internal fun notify(@StringRes message: Int) =
      Snackbar.make(viewContainer, message, Snackbar.LENGTH_SHORT).show()

  internal fun notify(message: String) =
      Snackbar.make(viewContainer, message, Snackbar.LENGTH_SHORT).show()

}
