package com.mohamedmenasy.revoluttask.base.platform

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mohamedmenasy.revoluttask.R
import com.mohamedmenasy.revoluttask.R.id
import com.mohamedmenasy.revoluttask.base.extension.inTransaction

/**
 * Base Activity class with helper methods for handling fragment transactions and back button
 * events.
 *
 * @see AppCompatActivity
 */
abstract class BaseActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_layout)
    addFragment(savedInstanceState)
  }

  private fun addFragment(savedInstanceState: Bundle?) =
      savedInstanceState ?: supportFragmentManager.inTransaction {
        add(
            id.fragmentContainer, fragment()
        )
      }

  abstract fun fragment(): BaseFragment
}
