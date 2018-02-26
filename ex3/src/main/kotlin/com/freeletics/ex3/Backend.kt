package com.freeletics.ex3

import io.reactivex.Single

/**
 *  load data from a backend (mimics a retrofit service interface)
 */
interface Backend {

    fun loadPersons(): Single<List<Person>>
}