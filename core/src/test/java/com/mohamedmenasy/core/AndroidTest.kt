package com.mohamedmenasy.core

import android.app.Application
import android.content.Context
import com.mohamedmenasy.core.AndroidTest.ApplicationStub
import org.junit.Rule
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config

/**
 * Base class for Robolectric data layer tests.
 * Inherit from this class to create a test.
 */
@RunWith(RobolectricTestRunner::class)
@Config(
    constants = BuildConfig::class,
    application = ApplicationStub::class,
    sdk = [21]
)
abstract class AndroidTest {

  @Suppress("LeakingThis")
  @Rule @JvmField val injectMocks = InjectMocksRule.create(this@AndroidTest)

  fun context(): Context = RuntimeEnvironment.application

  internal class ApplicationStub : Application()
}
