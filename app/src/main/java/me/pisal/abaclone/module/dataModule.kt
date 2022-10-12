package me.pisal.abaclone.module

import me.pisal.abaclone.data.repo.account.AccountsRepository
import me.pisal.abaclone.data.repo.account.AccountsRepositoryImpl
import me.pisal.abaclone.data.repo.auth.AuthRepository
import me.pisal.abaclone.data.repo.auth.AuthRepositoryImpl
import me.pisal.abaclone.data.repo.menu.MenusRepository
import me.pisal.abaclone.data.repo.menu.RemoteMenusRepository
import org.koin.dsl.module

val dataModule = module {
    single<AuthRepository> { AuthRepositoryImpl() }
    single<AccountsRepository> { AccountsRepositoryImpl() }
    single<MenusRepository> { RemoteMenusRepository(get()) }
}