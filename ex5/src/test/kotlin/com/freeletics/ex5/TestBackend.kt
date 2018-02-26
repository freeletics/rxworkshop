package com.freeletics.ex5

import io.reactivex.Single
import java.io.IOException
import java.util.concurrent.atomic.AtomicInteger

class TestBackend(private val failAfterEachNumberOfRequests: Int = 0) : Backend {

    val requests = AtomicInteger(failAfterEachNumberOfRequests)

    val exception = IOException("Fake IO Exception")


    val DATA = arrayOf(
            listOf(
                    Person(firstname = "First1", lastname = "Last1"),
                    Person(firstname = "First2", lastname = "Last2")
            ), listOf(
            Person(firstname = "First3", lastname = "Last3"),
            Person(firstname = "First4", lastname = "Last4")
    ), listOf(
            Person(firstname = "First5", lastname = "Last5"),
            Person(firstname = "First6", lastname = "Last6")
    )
    )

    val pullToRefreshList = listOf(Person(firstname = "Peter", lastname = "Pull-To-Refresh"))

    private fun shouldFail() = if (failAfterEachNumberOfRequests <= 0) false else {
        val requests = requests.incrementAndGet()
        requests % failAfterEachNumberOfRequests == 0
    }

    override fun loadPersons(page: Int): Single<List<Person>> {
        println("<<< Backend received loadPersons($page)")
        if (page < 0)
            throw IllegalArgumentException("page < 0")

        if (shouldFail()) {
            println("<<< Backend sends fake error")
            return Single.error(exception)
        }

        return (if (page >= DATA.size) Single.just(emptyList()) else Single.just(DATA[page]))
                .doOnSuccess {
                    println("<<< Backend answer for loadPersons($page) is $it")
                }
    }

    override fun loadNewestPage(): Single<List<Person>> {
        if (shouldFail()) {
            println("<<< Backend sends fake error")
            return Single.error(exception)
        }

        println("<<< Backend received loadNewestPage()")
        println("<<< Backend answer loadNewestPage() is $pullToRefreshList")

        return Single.just(pullToRefreshList)
    }
}