package me.pisal.abaclone.scene.account

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import me.pisal.abaclone.model.entity.Account
import java.math.BigDecimal

class AccountsViewModel : ViewModel() {

    fun accounts() = liveData {
        emit(
            listOf(
                Account(
                    "000533492", "Savings Account with ATM facility", BigDecimal(1999.12), true, "000 533 492", "USD", "#52BAD0"
                ),
                Account(
                    "010855111", "Mobile Savings Account Resident", BigDecimal(122345.12), false, "010 855 111", "KHR", "#52BAD0"
                )
            )
        )
    }
}