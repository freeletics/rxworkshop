package com.freeletics.ex3

import io.reactivex.Single
import java.io.IOException

class TestBackend(val fail: Boolean) : Backend {

    val DATA = listOf(
            Person(firstname = "Timo", lastname = "Gebhart"),
            Person(firstname = "Daniel", lastname = "Bierofka")
    )

    val EXCEPTION = IOException("Faked IO Exception")

    override fun loadPersons(): Single<List<Person>> = Single.create { emitter ->
        if (!emitter.isDisposed) {
            if (fail) {
                println("<<< Backend: Error has occurred")
                emitter.onError(EXCEPTION)
            } else {
                println("<<< Backend: Sending response with data $DATA")
                emitter.onSuccess(DATA)
            }
        }
    }
}