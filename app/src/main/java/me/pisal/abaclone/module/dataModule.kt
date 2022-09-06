package me.pisal.abaclone.module

import me.pisal.abaclone.model.data.account.AccountRepository
import me.pisal.abaclone.model.data.account.AccountRepositoryImpl
import me.pisal.abaclone.model.data.auth.AuthRepository
import me.pisal.abaclone.model.data.auth.AuthRepositoryImpl
import org.koin.dsl.module

val dataModule = module {
    single<AuthRepository> { AuthRepositoryImpl() }
    single<AccountRepository> { AccountRepositoryImpl() }
}