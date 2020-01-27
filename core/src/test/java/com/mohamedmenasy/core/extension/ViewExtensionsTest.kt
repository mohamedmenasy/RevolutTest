package com.mohamedmenasy.core.extension

import android.view.View
import com.mohamedmenasy.core.AndroidTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class ViewExtensionsTest : AndroidTest() {

  private lateinit var testView: View

  @Before fun setUp() {
    testView = View(context())
  }

  @Test fun `verify view is gone`() {
    testView.invisible()
    assertEquals(testView.visibility, View.GONE)
  }

  @Test fun `verify view is visible`() {
    testView.visible()
    assertEquals(testView.visibility, View.VISIBLE)
  }
}