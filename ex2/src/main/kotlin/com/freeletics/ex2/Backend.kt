package com.freeletics.ex2

import io.reactivex.Observable

interface Backend {
    fun loadPerson(personId: Int): Observable<Person>
    fun loadAddress(personId: Int): Observable<Address>
}
