package com.freeletics.ex1

import io.reactivex.Observable

class SearchRepository(private val view: View, private val backend: Backend) {


    fun search(): Observable<List<Person>> {

        //
        // TODO: implement the search call according the following criteria:
        //  - The search query string must contain at least 3 characters
        //  - To save backend traffic: only search if "search query" hasn't changed within the last 300 ms
        //  - If the user is typing fast "Hannes" and than deletes and types "Hannes" again (exceeding 300 ms) the search should not execute twice.
        //

        TODO("implement me! use view.onSearchTextChanged()")
        // return view.onSearchTextChanged() < continue here >
    }
}
