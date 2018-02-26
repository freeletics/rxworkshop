package com.freeletics.ex5

import io.reactivex.Single

/**
 *  load data from a backend (mimics a retrofit service interface)
 */
interface Backend {

    /**
     * Load persons. Parameter page starts at 1
     */
    fun loadPersons(page: Int): Single<List<Person>>

    /**
     * This must be called on a pull to refresh
     */
    fun loadNewestPage(): Single<List<Person>>
}