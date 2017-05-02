package com.github.mbarrben.moviedb.misc

import org.junit.Test
import kotlin.test.assertEquals

class EmojiFlagShould {

  @Test(expected = IllegalStateException::class) fun throwIllegalStateExceptionOnInputLongerThan2Chars() {
    emojiFlag("long_string")
  }

  @Test(expected = IllegalStateException::class) fun throwIllegalStateExceptionOnInputShorterThan2Chars() {
    emojiFlag("l")
  }

  @Test fun returnSquaredABWhenInputIsAB() {
    assertEquals("🇦🇧", emojiFlag("AB"))
  }
}