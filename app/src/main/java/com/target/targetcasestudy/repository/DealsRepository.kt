package com.target.targetcasestudy.repository

import com.target.targetcasestudy.api.DealsApi
import com.target.targetcasestudy.data.DealItem
import com.target.targetcasestudy.data.Products
import io.reactivex.Single
import javax.inject.Inject

interface DealsRepository {
    fun getAllDeals(): Single<Products>
    fun getEachDealDetails(dealId: String): Single<DealItem>
}

/**
 * This will implement DealsRepository & provide its implementation.
 * It takes Api endpoint as its params
 * @param dealApi
 */
class DealsRepositoryImpl @Inject constructor(
    private val dealApi: DealsApi
) : DealsRepository {

    override fun getAllDeals() = dealApi.getAllDeals()
    override fun getEachDealDetails(dealId: String) = dealApi.getEachDealDetails(dealId)
}