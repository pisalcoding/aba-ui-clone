package me.pisal.abaclone.di

import me.pisal.abaclone.data.repo.account.AccountsRepository
import me.pisal.abaclone.data.repo.account.LocalAccountsRepository
import me.pisal.abaclone.data.repo.auth.AuthRepository
import me.pisal.abaclone.data.repo.auth.LocalAuthRepository
import me.pisal.abaclone.data.repo.menu.LocalMenusRepository
import me.pisal.abaclone.data.repo.menu.MenusRepository
import org.koin.dsl.module

val persistenceModule = module {

    single<MenusRepository>(qualifier = RepoQualifier.Local) {
        LocalMenusRepository()
    }

    single<AccountsRepository>(qualifier = RepoQualifier.Local) {
        LocalAccountsRepository()
    }

    single<AuthRepository>(qualifier = RepoQualifier.Local) {
        LocalAuthRepository()
    }
}