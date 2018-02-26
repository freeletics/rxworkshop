package com.freeletics.ex3


sealed class State {


    /**
     * Indicates that loading is in progress
     */
    object Loading : State()


    /**
     * The loaded data has been loaded successfully
     */
    data class Content(val persons : List<Person>) : State()


    /**
     * An error has been occurred while loading data
     */
    data class Error(val throwable: Throwable) : State()


}