package com.mohamedmenasy.revoluttask

import android.app.Application
import android.content.Context
import com.mohamedmenasy.revoluttask.AndroidTest.ApplicationStub
import com.mohamedmenasy.revoluttask.base.platform.BaseActivity
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mockito
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
  @Rule
  @JvmField
  val injectMocks = InjectMocksRule.create(this@AndroidTest)

  fun context(): Context = RuntimeEnvironment.application
  fun activityContext(): Context = Mockito.mock(BaseActivity::class.java)

  internal class ApplicationStub : Application()
}
