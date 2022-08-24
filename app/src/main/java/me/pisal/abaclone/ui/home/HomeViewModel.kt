package me.pisal.abaclone.ui.home

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import me.pisal.abaclone.R
import me.pisal.abaclone.model.entity.MenuModel

class HomeViewModel : ViewModel() {

    fun gridMenus(context: Context) = liveData {
        emit(
            listOf(
                MenuModel("1", context.getString(R.string.accounts), R.drawable.ic_accounts_home_menu),
                MenuModel("2", context.getString(R.string.cards), R.drawable.ic_cards_home_menu),
                MenuModel("3", context.getString(R.string.payments), R.drawable.ic_payments_home_menu),
                MenuModel("4", context.getString(R.string.new_account), R.drawable.ic_new_account_home_menu),
                MenuModel("5", context.getString(R.string.cash_to_atm), R.drawable.ic_ecash_home_menu),
                MenuModel("6", context.getString(R.string.transfers), R.drawable.ic_transfers_home_menu),
                MenuModel("7", context.getString(R.string.scan_qr), R.drawable.ic_scan_qr_home_menu),
                MenuModel("8", context.getString(R.string.loans), R.drawable.ic_loans_home_menu),
                MenuModel("9", context.getString(R.string.services), R.drawable.ic_services_home_menu),
            )
        )
    }
}