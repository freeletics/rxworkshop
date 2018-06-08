package com.freeletics.ex3

import io.reactivex.Observable

sealed class State {


    /**
     * Indicates that loading is in progress
     */
    object Loading : State()


    /**
     * The loaded data has been loaded successfully
     */
    data class Content(val persons: List<Person>) : State()


    /**
     * An error has been occurred while loading data
     */
    data class Error(val throwable: Throwable) : State()


}

/**
 * Repositor Pattern as a state machine
 */
class PersonRepositoryStatemachine(private val backend: Backend) {


    val state: Observable<State> = TODO("Implement me!")

    //
    // When subscribing to this observable,
    // it should start to load data from backend. The following states should be emmitted:
    // 1. Before starting loading State.Loading must be emitted
    // 2. Next either State.Content or State.Error must be emitted in case that an error occurs
    //
    // hint: return backend.loadPersons(). < put your code here >
    //


}