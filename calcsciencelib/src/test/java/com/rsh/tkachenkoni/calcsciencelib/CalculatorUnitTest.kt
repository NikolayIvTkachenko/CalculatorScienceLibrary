package com.rsh.tkachenkoni.calcsciencelib

import com.rsh.tkachenkoni.calcsciencelib.executions.Calculator
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class CalculatorUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals("2+2=4", Calculator.calculate("2+2"))
    }

    @Test
    fun additionPluralBracketIsCorrect() {
        assertEquals("(2+2)*3=12", Calculator.calculate("(2+2)*3"))
    }

    @Test
    fun difficult001BracketIsCorrect() {
        assertEquals("2*(20-(2+2)*3)=16", Calculator.calculate("2*(20-(2+2)*3)"))
    }
}