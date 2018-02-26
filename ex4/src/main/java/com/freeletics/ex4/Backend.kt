package com.freeletics.ex4

import io.reactivex.Single

/**
 *  load data from a backend (mimics a retrofit service interface)
 */
interface Backend {

    /**
     * Load persons. Parameter page starts at 1
     */
    fun loadPersons(page : Int): Single<List<Person>>

}