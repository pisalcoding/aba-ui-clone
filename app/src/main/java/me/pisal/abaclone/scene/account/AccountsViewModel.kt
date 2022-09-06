package me.pisal.abaclone.scene.account

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import me.pisal.abaclone.common.Currency.KHR
import me.pisal.abaclone.common.Currency.USD
import me.pisal.abaclone.model.entity.Account
import me.pisal.abaclone.model.entity.sumBalanceByCurrency

class AccountsViewModel : ViewModel() {

    private val _accounts = MutableLiveData<List<Account>>()
    fun accountsUpdated(accounts: List<Account>) {
        _accounts.value = accounts
    }

    val totalInKhr = _accounts.switchMap {
        liveData { emit(it.sumBalanceByCurrency(KHR)) }
    }

    val totalInUsd = _accounts.switchMap {
        liveData { emit(it.sumBalanceByCurrency(USD)) }
    }
}