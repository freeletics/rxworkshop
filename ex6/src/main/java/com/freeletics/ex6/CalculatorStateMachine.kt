package com.freeletics.ex6

import com.jakewharton.rxrelay2.PublishRelay
import io.reactivex.Observable

sealed class Input {

    data class Add(val value: Int) : Input()

    data class Sub(val value: Int) : Input()

    data class Mul(val value: Int) : Input()

    data class Div(val value: Int) : Input()
}


class CalculatorStateMachine {

    val input = PublishRelay.create<Input>()

    /**
     * Every calculator must start with zero value.
     */
    val state: Observable<Int> = TODO("Implement me")
}