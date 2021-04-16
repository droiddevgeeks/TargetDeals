package com.target.targetcasestudy.api

import com.target.targetcasestudy.data.DealItem
import com.target.targetcasestudy.data.Products
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface DealsApi {

    @GET("v1/deals")
    fun getAllDeals(): Single<Products>

    @GET("v1/deals/{dealId}")
    fun getEachDealDetails(@Path("dealId") dealId: String): Single<DealItem>
}