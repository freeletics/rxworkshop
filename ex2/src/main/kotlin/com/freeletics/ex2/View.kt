package com.freeletics.ex2

import io.reactivex.Observable

interface View {
    /**
     * Emittes the id of the person
     */
    fun onPersonClicked(): Observable<Int>

}
