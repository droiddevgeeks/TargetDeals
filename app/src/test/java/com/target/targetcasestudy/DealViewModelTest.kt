package com.target.targetcasestudy

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.*
import com.target.targetcasestudy.common.Event
import com.target.targetcasestudy.common.RxScheduler
import com.target.targetcasestudy.data.DealItem
import com.target.targetcasestudy.data.DealsState
import com.target.targetcasestudy.data.Products
import com.target.targetcasestudy.ui.deals.DealsViewModel
import com.target.targetcasestudy.usecase.DealsUseCase
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.Assertions.*

class DealViewModelTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private val useCase by lazy { mock<DealsUseCase>() }
    private val scheduler = mock<RxScheduler> {
        on { io } doReturn Schedulers.trampoline()
        on { main } doReturn Schedulers.trampoline()
    }

    private val viewModel by lazy { DealsViewModel(useCase, scheduler) }

    private val dealStateObserver by lazy { mock<Observer<Event<DealsState<Products>>>>() }
    private val dealItemStateObserver by lazy { mock<Observer<Event<DealsState<DealItem>>>>() }

    @Test
    fun `should return all deals`() {
        val dealsResponse = mock<Products>()
        val argCaptor = argumentCaptor<Event<DealsState<Products>>>()
        whenever(useCase.getAllDeals()).thenReturn(Single.just(dealsResponse))
        viewModel.dealsLiveData.observeForever(dealStateObserver)

        viewModel.fetchAllDeals()

        /**
         * Loading True, Loading false, Data
         */
        verify(dealStateObserver, times(3)).onChanged(argCaptor.capture())

        argCaptor.allValues.forEach {
            when (val state = it.peekContent()) {
                is DealsState.Loading -> {
                    if (state.isLoading) assertTrue(state.isLoading)
                    else assertFalse(state.isLoading)
                }
                is DealsState.Error -> assertNull(state.error)
                is DealsState.Success -> assertEquals(state.data, dealsResponse)
            }
        }
    }

    @Test
    fun `should return error when fetching deals`() {
        val dealsError = mock<Throwable>()
        val argCaptor = argumentCaptor<Event<DealsState<Products>>>()
        whenever(useCase.getAllDeals()).thenReturn(Single.error(dealsError))
        viewModel.dealsLiveData.observeForever(dealStateObserver)

        viewModel.fetchAllDeals()
        /**
         * Loading True, Loading false, Loading False, State error
         */
        verify(dealStateObserver, times(4)).onChanged(argCaptor.capture())

        argCaptor.allValues.forEach {
            when (val state = it.peekContent()) {
                is DealsState.Loading -> {
                    if (state.isLoading) assertTrue(state.isLoading)
                    else assertFalse(state.isLoading)
                }
                is DealsState.Error -> assertEquals(state.error, dealsError)
                is DealsState.Success -> assertNull(state.data)
            }
        }
    }

    @Test
    fun `should return single deals product`() {
        val dealsResponse = mock<DealItem>()
        val argCaptor = argumentCaptor<Event<DealsState<DealItem>>>()
        whenever(useCase.getEachDealDetails("1234")).thenReturn(Single.just(dealsResponse))
        viewModel.dealDetailLiveData.observeForever(dealItemStateObserver)

        viewModel.fetchDealDetails("1234")

        /**
         * Loading True, Loading false, Data
         */
        verify(dealItemStateObserver, times(3)).onChanged(argCaptor.capture())

        argCaptor.allValues.forEach {
            when (val state = it.peekContent()) {
                is DealsState.Loading -> {
                    if (state.isLoading) assertTrue(state.isLoading)
                    else assertFalse(state.isLoading)
                }
                is DealsState.Error -> assertNull(state.error)
                is DealsState.Success -> assertEquals(state.data, dealsResponse)
            }
        }
    }

    @Test
    fun `should return error when fetching single deal deals product`() {
        val dealsError = mock<Throwable>()
        val argCaptor = argumentCaptor<Event<DealsState<DealItem>>>()
        whenever(useCase.getEachDealDetails("1234")).thenReturn(Single.error(dealsError))
        viewModel.dealDetailLiveData.observeForever(dealItemStateObserver)

        viewModel.fetchDealDetails("1234")
        /**
         * Loading True, Loading false, Loading False, State error
         */
        verify(dealItemStateObserver, times(4)).onChanged(argCaptor.capture())

        argCaptor.allValues.forEach {
            when (val state = it.peekContent()) {
                is DealsState.Loading -> {
                    if (state.isLoading) assertTrue(state.isLoading)
                    else assertFalse(state.isLoading)
                }
                is DealsState.Error -> assertEquals(state.error, dealsError)
                is DealsState.Success -> assertNull(state.data)
            }
        }
    }
}