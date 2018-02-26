package com.freeletics.ex1

import io.reactivex.Observable

interface Backend {

    fun searchfor(searchFor: String): Observable<List<Person>>
}
