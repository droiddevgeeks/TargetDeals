package com.target.targetcasestudy

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import com.nhaarman.mockitokotlin2.whenever
import com.target.targetcasestudy.data.DealItem
import com.target.targetcasestudy.data.Products
import com.target.targetcasestudy.repository.DealsRepository
import io.reactivex.Single
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class DealsUseCaseImplTest {

    private val repo by lazy { mock<DealsRepository>() }

    @Test
    fun `should return all Deals successfully`() {

        val dealsResponse = mock<Products>()
        whenever(repo.getAllDeals()).thenReturn(Single.just(dealsResponse))

        repo.getAllDeals()
            .test()
            .assertComplete()
            .assertValueCount(1)
            .assertValue {
                assertEquals(dealsResponse, it)
                true
            }

        verify(repo).getAllDeals()
        verifyNoMoreInteractions(repo)
    }

    @Test
    fun `should return deals item successfully`() {

        val dealItemResponse = mock<DealItem>()
        val dealId = "1234"
        whenever(repo.getEachDealDetails(dealId))
            .thenReturn(Single.just(dealItemResponse))

        repo.getEachDealDetails(dealId)
            .test()
            .assertComplete()
            .assertValueCount(1)
            .assertValue {
                assertEquals(dealItemResponse, it)
                true
            }

        verify(repo).getEachDealDetails(dealId)
        verifyNoMoreInteractions(repo)
    }
}