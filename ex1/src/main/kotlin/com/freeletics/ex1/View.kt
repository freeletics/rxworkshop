package com.freeletics.ex1

import io.reactivex.Observable

interface View {

    fun onSearchTextChanged(): Observable<String>
}
