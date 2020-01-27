package com.mohamedmenasy.core.extension

import com.mohamedmenasy.core.UnitTest
import org.junit.Test
import kotlin.test.assertEquals

class ExtensionsTest : UnitTest() {
  companion object {
    private const val LONGDATA = 1572122675L
  }

  @Test fun `verify the unix timestamp to full date`() {
    val formatedDate = LONGDATA.toFullDate()
    assertEquals(formatedDate, "19-10-26, 22:44:35")
  }

  @Test fun `verify the unix timestamp to date`() {
    val formatedDate = LONGDATA.toDate()
    assertEquals(formatedDate, "19-10-26")
  }

  @Test fun `verify the unix timestamp to time`() {
    val formatedDate = LONGDATA.toTime()
    assertEquals(formatedDate, "22:44:35")
  }

  @Test fun `verify empty string function`() {
    assertEquals(String.Companion.empty(), "")
  }
}