package com.target.targetcasestudy.usecase

import com.target.targetcasestudy.data.DealItem
import com.target.targetcasestudy.data.Products
import com.target.targetcasestudy.repository.DealsRepository
import io.reactivex.Single
import javax.inject.Inject

interface DealsUseCase {
    fun getAllDeals(): Single<Products>
    fun getEachDealDetails(dealId: String): Single<DealItem>
}

/**
 * This will implement DealsUseCase & provide its usecase implementation.
 * It takes Repository  as its params
 * @param repo : DealsRepository
 */
class DealsUseCaseImpl @Inject constructor(private val repo: DealsRepository) : DealsUseCase {

    override fun getAllDeals() = repo.getAllDeals()
    override fun getEachDealDetails(dealId: String) = repo.getEachDealDetails(dealId)
}