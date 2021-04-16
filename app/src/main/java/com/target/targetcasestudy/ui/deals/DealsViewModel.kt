package com.target.targetcasestudy.ui.deals

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.target.targetcasestudy.common.Event
import com.target.targetcasestudy.common.RxScheduler
import com.target.targetcasestudy.data.DealItem
import com.target.targetcasestudy.data.DealsState
import com.target.targetcasestudy.data.Products
import com.target.targetcasestudy.usecase.DealsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

@HiltViewModel
class DealsViewModel @Inject constructor(
    private val dealsUseCase: DealsUseCase,
    private val scheduler: RxScheduler
) : ViewModel() {

    /**
     * _dealsLiveData : this is mutable type with private visibility
     * which ensure that only inside viewModel  we can update its value.
     * dealsLiveData : this is public which means we can access it from outside viewModel class
     * but we can't set value from outside as it is not mutable type.
     */
    private val _dealsLiveData by lazy { MutableLiveData<Event<DealsState<Products>>>() }
    val dealsLiveData: LiveData<Event<DealsState<Products>>> by lazy { _dealsLiveData }

    private val _dealDetailLiveData by lazy { MutableLiveData<Event<DealsState<DealItem>>>() }
    val dealDetailLiveData: LiveData<Event<DealsState<DealItem>>> by lazy { _dealDetailLiveData }

    private val disposable by lazy { CompositeDisposable() }


    /**
     * This method fetch all list of deals from API.
     */
    fun fetchAllDeals() {
        val dealsDisposable = dealsUseCase.getAllDeals()
            .subscribeOn(scheduler.io)
            .observeOn(scheduler.main)
            .doOnSubscribe { _dealsLiveData.value = Event(DealsState.Loading(true)) }
            .doOnEvent { _, _ -> _dealsLiveData.value = Event(DealsState.Loading(false)) }
            .doOnError { _dealsLiveData.value = Event(DealsState.Loading(false)) }
            .subscribe(
                { Event(DealsState.Success(it)).run(_dealsLiveData::postValue) },
                { Event(DealsState.Error(it)).run(_dealsLiveData::postValue) }
            )
        disposable.add(dealsDisposable)
    }

    fun fetchDealDetails(dealId: String) {
        val commentDisposable = dealsUseCase.getEachDealDetails(dealId)
            .subscribeOn(scheduler.io)
            .observeOn(scheduler.main)
            .doOnSubscribe { _dealDetailLiveData.value = Event(DealsState.Loading(true)) }
            .doOnEvent { _, _ -> _dealDetailLiveData.value = Event(DealsState.Loading(false)) }
            .doOnError { _dealDetailLiveData.value = Event(DealsState.Loading(false)) }
            .subscribe(
                { Event(DealsState.Success(it)).run(_dealDetailLiveData::postValue) },
                { Event(DealsState.Error(it)).run(_dealDetailLiveData::postValue) }
            )
        disposable.add(commentDisposable)
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}