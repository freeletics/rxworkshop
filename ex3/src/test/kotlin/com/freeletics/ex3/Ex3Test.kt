package com.freeletics.ex3

import io.reactivex.observers.TestObserver
import org.junit.Before
import org.junit.Test
import java.util.concurrent.TimeUnit

class Ex3Test {

    @Before
    fun niceConsole(){
        println("--------------- Starting a unit test ---------------")
    }


    @Test
    fun `Loading state followed by Content state`() {
        val backend = TestBackend(false)
        val repo = PersonRepositoryStatemachine(backend)
        println(">>> Subscribing to PersonRepository")

        val subscriber = TestObserver<State>()
        repo.state.subscribe(subscriber)

        subscriber.await(5, TimeUnit.SECONDS)

        subscriber.assertValues(State.Loading, State.Content(backend.DATA))
    }


    @Test
    fun `Loading state followed by Error state`() {
        val backend = TestBackend(true)
        val repo = PersonRepositoryStatemachine(backend)
        println(">>> Subscribing to PersonRepository")

        val subscriber = TestObserver<State>()
        repo.state.subscribe(subscriber)

        subscriber.await(5, TimeUnit.SECONDS)

        subscriber.assertValues(State.Loading, State.Error(backend.EXCEPTION))
    }

}