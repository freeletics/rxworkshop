package com.freeletics.ex5.pagination

import com.freeletics.ex5.Backend
import com.freeletics.ex5.Person
import com.jakewharton.rxrelay2.PublishRelay
import io.reactivex.Observable


sealed class Input {

    /**
     * This action is triggered at the very beginning to start loading the first page
     */
    object LoadFirstPage : Input() {
        override fun toString(): String {
            return "Input.Load"
        }
    }

    /**
     * In case that an error has occurred this input can be triggered to try reload the first page
     */
    object RetryLoadFirstPage : Input() {
        override fun toString(): String {
            return "Input.Retry"
        }
    }

    /**
     * Indicates to load the next Page
     */
    object LoadNextPage : Input() {
        override fun toString(): String {
            return "Input.NextPage"
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
     * Loading the next page is in progress
     */
    data class NextPageLoading(val data: List<Person>) : State()

    /**
     * An error has occured while trying to load the next page
     */
    data class NextPageError(val throwable: Throwable, val data: List<Person>) : State()

}

class Psm(
        private val backend: Backend
)