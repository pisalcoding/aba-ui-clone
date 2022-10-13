package me.pisal.abaclone.module

import me.pisal.abaclone.data.repo.account.AccountsRepository
import me.pisal.abaclone.data.repo.account.AccountsRepositoryImpl
import me.pisal.abaclone.data.repo.auth.AuthRepository
import me.pisal.abaclone.data.repo.auth.AuthRepositoryImpl
import me.pisal.abaclone.data.repo.menu.MenusRepository
import me.pisal.abaclone.data.repo.menu.RemoteMenusRepository
import org.koin.dsl.module

val RemoteDataModule = module {
    single<AuthRepository>(qualifier = RepoQualifier.Remote) { AuthRepositoryImpl() }
    single<AccountsRepository>(qualifier = RepoQualifier.Remote) { AccountsRepositoryImpl() }
    single<MenusRepository>(qualifier = RepoQualifier.Remote) { RemoteMenusRepository(get()) }
}