package me.pisal.abaclone.model.data.account

import me.pisal.abaclone.common.TResult
import me.pisal.abaclone.model.dto.ResponseWrapper
import me.pisal.abaclone.model.entity.Account
import java.math.BigDecimal

class AccountRepositoryImpl : AccountRepository {

    override suspend fun list(): TResult<ResponseWrapper<List<Account>>> {
        val accounts = listOf(
            Account(
                id = "000533492",
                title = "Savings Account with ATM facility",
                category = "Savings",
                balance = BigDecimal(1999.12),
                primary = true,
                accountNo = "000 533 492",
                currency = "USD",
                colorHex = "#52BAD0"
            ),
            Account(
                id = "010855111",
                title = "Mobile Savings Account Resident",
                category = "Mobile Savings",
                balance = BigDecimal(122345.12),
                primary = false,
                accountNo = "010 855 111",
                currency = "KHR",
                colorHex = "#925FB1"
            )
        )
        return TResult.Success(
            ResponseWrapper(
                data = accounts
            )
        )
    }
}