package com.freeletics.ex5

import com.freeletics.ex5.pagination.Psm
import com.jakewharton.rxrelay2.ReplayRelay
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.util.concurrent.TimeUnit


class Ex5Test {

    @Before
    fun niceConsole() {
        println("--------------- Starting a unit test ---------------")
    }

    @Test
    fun `load first Page successfully`() {
        val recordedStates = ReplayRelay.create<State>()

        val backend = TestBackend()
        val sm = PaginationWithPullToRefreshStateMachine(backend, Psm(backend))
        sm.state.subscribe(recordedStates)

        println(">>> Triggering ${Input.Load}")
        sm.input.accept(Input.Load)

        recordedStates.assertStateReceived(
                State.FirstPageLoading,
                State.Content(backend.DATA[0])
        )
    }


    @Test
    fun `load first Page fails and retrying is successful`() {
        val recordedStates = ReplayRelay.create<State>()

        val backend = TestBackend(failAfterEachNumberOfRequests = 1)
        val sm = PaginationWithPullToRefreshStateMachine(backend, Psm(backend))
        sm.state.subscribe(recordedStates)

        println(">>> Triggering ${Input.Load}")
        sm.input.accept(Input.Load)

        println(">>> Triggering ${Input.Retry}")
        sm.input.accept(Input.Retry)

        recordedStates.assertStateReceived(
                State.FirstPageLoading,
                State.FirstPageLoadingError(backend.exception),
                State.FirstPageLoading,
                State.Content(backend.DATA[0])
        )
    }


    @Test
    fun `load first Page successfully but pull to refresh fails`() {
        val recordedStates = ReplayRelay.create<State>()

        val backend = TestBackend(failAfterEachNumberOfRequests = 2)
        val sm = PaginationWithPullToRefreshStateMachine(backend, Psm(backend))
        sm.state.subscribe(recordedStates)

        println(">>> Triggering ${Input.Load}")
        sm.input.accept(Input.Load)

        println(">>> Triggering ${Input.PullToRefresh}")
        sm.input.accept(Input.PullToRefresh)

        recordedStates.assertStateReceived(
                State.FirstPageLoading,
                State.Content(backend.DATA[0]),
                State.PullToRefreshLoading(backend.DATA[0]),
                State.PullToRefreshError(backend.exception, backend.DATA[0])
        )
    }


    @Test
    fun `load first Page and second Page successfully but third Page fails`() {
        val recordedStates = ReplayRelay.create<State>()

        val backend = TestBackend(failAfterEachNumberOfRequests = 3)
        val sm = PaginationWithPullToRefreshStateMachine(backend, Psm(backend))
        sm.state.subscribe(recordedStates)

        println(">>> Triggering ${Input.Load}")
        sm.input.accept(Input.Load)

        println(">>> Triggering ${Input.NextPage}")
        sm.input.accept(Input.NextPage)

        println(">>> Triggering ${Input.NextPage}")
        sm.input.accept(Input.NextPage)

        recordedStates.assertStateReceived(
                State.FirstPageLoading,
                State.Content(backend.DATA[0]),
                State.NextPageLoading(backend.DATA[0]),
                State.Content(backend.DATA[0] + backend.DATA[1]),
                State.NextPageLoading(backend.DATA[0] + backend.DATA[1]),
                State.NextPageError(backend.exception, backend.DATA[0] + backend.DATA[1])
        )
    }


    @Test
    fun `load first Page, second Page, do pull to refresh and third Page successfully`() {
        val recordedStates = ReplayRelay.create<State>()

        val backend = TestBackend()
        val sm = PaginationWithPullToRefreshStateMachine(backend, Psm(backend))
        sm.state.subscribe(recordedStates)

        println(">>> Triggering ${Input.Load}")
        sm.input.accept(Input.Load)

        println(">>> Triggering ${Input.NextPage}")
        sm.input.accept(Input.NextPage)

        println(">>> Triggering ${Input.PullToRefresh}")
        sm.input.accept(Input.PullToRefresh)

        println(">>> Triggering ${Input.NextPage}")
        sm.input.accept(Input.NextPage)

        recordedStates.assertStateReceived(
                State.FirstPageLoading,
                State.Content(backend.DATA[0]),
                State.NextPageLoading(backend.DATA[0]),
                State.Content(backend.DATA[0] + backend.DATA[1]),
                State.PullToRefreshLoading(backend.DATA[0] + backend.DATA[1]),
                State.Content(backend.pullToRefreshList + backend.DATA[0] + backend.DATA[1]),
                State.NextPageLoading(backend.pullToRefreshList + backend.DATA[0] + backend.DATA[1]),
                State.Content(backend.pullToRefreshList + backend.DATA[0] + backend.DATA[1] + backend.DATA[2])
        )
    }


    private fun ReplayRelay<State>.assertStateReceived(vararg states: State) {

        val actual = take(states.size.toLong())
                .timeout(10, TimeUnit.SECONDS)
                .toList()
                .blockingGet()

        Assert.assertEquals(states.toList(), actual)
    }
}