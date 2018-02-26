package com.freeletics.ex3

import io.reactivex.Observable

/**
 * Repositor Pattern as a state machine
 */
class PersonRepositoryStatemachine(private val backend: Backend) {


    fun loadPersons(): Observable<State> {

        //
        // When subscribing to this observable,
        // it should start to load data from backend. The following states should be emmitted:
        // 1. Before starting loading State.Loading must be emitted
        // 2. Next either State.Content or State.Error must be emitted in case that an error occurs
        //
        // hint: return backend.loadPersons(). < put your code here >
        //

        TODO("Implement me!")
    }
}