package com.freeletics.ex6

import com.jakewharton.rxrelay2.ReplayRelay
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class Ex5Test {

    @Before
    fun niceConsole() {
        println("--------------- Starting a unit test ---------------")
    }

    @Test
    fun `add 3, sub 1, mul 2, div 2`() {
        val recordedStates = ReplayRelay.create<Int>()

        val sm = CalculatorStateMachine()
        sm.state.subscribe(recordedStates)

        println(">>> Triggering ${Input.Add(3)}")
        sm.input.accept(Input.Add(3))

        println(">>> Triggering ${Input.Sub(1)}")
        sm.input.accept(Input.Sub(1))

        println(">>> Triggering ${Input.Mul(2)}")
        sm.input.accept(Input.Mul(2))

        println(">>> Triggering ${Input.Div(2)}")
        sm.input.accept(Input.Div(2))

        Assert.assertEquals(recordedStates.values.toList(), listOf(0, 3, 2, 4, 2))
    }
}