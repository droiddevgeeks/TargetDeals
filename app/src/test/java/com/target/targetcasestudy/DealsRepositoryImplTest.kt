package com.target.targetcasestudy

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.target.targetcasestudy.api.DealsApi
import com.target.targetcasestudy.data.DealItem
import com.target.targetcasestudy.data.Products
import io.reactivex.Single
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import retrofit2.HttpException
import retrofit2.Response

class DealsRepositoryImplTest {

    private val endpoint by lazy { mock<DealsApi>() }

    @Test
    fun `should return all deals when calling all deals api`() {
        val expectedResponse = mock<Products>()
        whenever(endpoint.getAllDeals())
            .thenReturn(Single.create { it.onSuccess(expectedResponse) })
        endpoint.getAllDeals()
            .test()
            .assertComplete()
            .assertNoErrors()
            .assertValue(expectedResponse)
    }


    @Test
    fun `should return error when fetching all deals`() {

        val body = Response.error<Products>(
            400,
            "{\"key\":[\"something went wrong\"]}".toResponseBody("application/json".toMediaTypeOrNull())
        )
        val exception = HttpException(body)

        whenever(endpoint.getAllDeals()).thenReturn(Single.error(exception))
        endpoint.getAllDeals()
            .test()
            .assertValueCount(0)
            .assertError { it is HttpException }

        verify(endpoint).getAllDeals()
    }


    @Test
    fun `should return selected deal item`() {
        val expectedResponse = mock<DealItem>()
        Mockito.`when`(endpoint.getEachDealDetails("1234"))
            .thenReturn(Single.create { it.onSuccess(expectedResponse) })
        endpoint.getEachDealDetails("1234")
            .test()
            .assertComplete()
            .assertNoErrors()
            .assertValue(expectedResponse)
    }


    @Test
    fun `should return error when fetching deal item`() {

        val body = Response.error<DealItem>(
            400,
            "{\"key\":[\"something went wrong\"]}".toResponseBody("application/json".toMediaTypeOrNull())
        )
        val exception = HttpException(body)

        whenever(endpoint.getEachDealDetails("1234"))
            .thenReturn(Single.error(exception))
        endpoint.getEachDealDetails("1234")
            .test()
            .assertValueCount(0)
            .assertError { it is HttpException }

        verify(endpoint).getEachDealDetails("1234")
    }

}