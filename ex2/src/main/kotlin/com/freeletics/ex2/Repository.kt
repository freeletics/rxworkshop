package com.freeletics.ex2

import io.reactivex.Observable

class Repository(private val view: View, private val backend: Backend) {

    fun loadPersons(): Observable<PersonWithAddress> {

        //
        // TODO: From the view layer a user of our app can click on a person to load the details and address about this person.
        //
        // Keep in mind that clicks can happen multiple times (even if previous results have not been received yet, don't interleave)
        //
        // return view.onPersonClicked().<-- your code to somehow load data from backend. Use backend.loadPerson(personId) and loadAddress(personId) -->
        //
        //
        TODO("Not implemented")
    }

}
