package com.target.targetcasestudy

import com.target.targetcasestudy.data.validateCreditCard
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

/**
 * Feel free to make modifications to these unit tests! Remember, you have full technical control
 * over the project, so you can use any libraries and testing strategies that see fit.
 */
class CreditCardValidatorTest {
  @Test
  fun `is credit card number valid`() {
    assertTrue(validateCreditCard("4539976741512043"))
  }

  @Test
  fun `Should fail for invalid credit card number`() {
    assertFalse(validateCreditCard("4539976712341512043")
    )
  }
}
