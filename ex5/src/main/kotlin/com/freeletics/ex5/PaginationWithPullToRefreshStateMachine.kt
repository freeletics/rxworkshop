package com.freeletics.ex5

import com.freeletics.ex5.pagination.Psm
import com.jakewharton.rxrelay2.PublishRelay
import io.reactivex.Observable


sealed class Input {

    /**
     * This action is triggered at the very beginning to start loading the first page
     */
    object Load : Input() {
        override fun toString(): String {
            return "Input.Load"
        }
    }

    /**
     * In case that an error has occurred this input can be triggered to try reload the first page
     */
    object Retry : Input() {
        override fun toString(): String {
            return "Input.Retry"
        }
    }

    /**
     * Indicates to load the next Page
     */
    object NextPage : Input() {
        override fun toString(): String {
            return "Input.NextPage"
        }
    }

    /**
     * Indicates that the user has triggered a Pull To Refresh
     */
    object PullToRefresh : Input() {
        override fun toString(): String {
            return "Input.PullToRefresh"
        }
    }

}


sealed class State {

    /**
     * The first page is loading
     */
    object FirstPageLoading : State()

    /**
     * An error has occurred while loading the first page
     */
    data class FirstPageLoadingError(val throwable: Throwable) : State()


    /**
     * Data (list of persons) has been loaded successfully and can be displayed on the view
     */
    data class Content(val data: List<Person>) : State()

    /**
     * A PullToRefresh has been triggered and loading is now in progress.
     * Once the View receives this event, a pull to refresh indicator is shown
     */
    data class PullToRefreshLoading(val data: List<Person>) : State()

    /**
     * Indicates that an error has occurred while doing a pull to refresh
     */
    data class PullToRefreshError(val throwable: Throwable, val data: List<Person>) : State()


    /**
     * Loading the next page is in progress
     */
    data class NextPageLoading(val data: List<Person>) : State()

    /**
     * An error has occured while trying to load the next page
     */
    data class NextPageError(val throwable: Throwable, val data: List<Person>) : State()

}

class PaginationWithPullToRefreshStateMachine(
        private val backend: Backend,
        private val psm : Psm
) {


    /**
     * The inputs
     */
    val input: PublishRelay<Input> = PublishRelay.create()


    /**
     * The states (a.k.a outputs).
     * Keep in mind that the State always has to represent the whole internal state so that if a view
     * would like to display just the state, it's as easy as just "rendering" the state on the screen.
     *
     * Also keep in mind, that the implementation should follow functional programming concepts:
     *  - Data structures like List<Person> must be immutable (i.e. don't add persons to an existing list but rather create a copy of "old list" and then add items
     *
     * hint: you may find the scan operator useful: http://reactivex.io/documentation/operators/scan.html
     */
    val state: Observable<State> = TODO( "Implement me")
}