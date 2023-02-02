package me.pisal.abaclone.module

import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import me.pisal.abaclone.data.repo.account.AccountsRepository
import me.pisal.abaclone.data.repo.account.AccountsRepositoryImpl
import me.pisal.abaclone.data.repo.auth.AuthRepository
import me.pisal.abaclone.data.repo.auth.AuthRepositoryImpl
import me.pisal.abaclone.data.repo.menu.LocalMenusRepository
import me.pisal.abaclone.data.repo.menu.MenusRepository
import me.pisal.abaclone.data.repo.menu.RemoteMenusRepository
import me.pisal.abaclone.model.dao.MbMenuDao
import org.koin.dsl.module

val remoteDataModule = module {
    single<AuthRepository>(qualifier = RepoQualifier.Remote) { AuthRepositoryImpl() }
    single<AccountsRepository>(qualifier = RepoQualifier.Remote) { AccountsRepositoryImpl() }
    single<MenusRepository>(qualifier = RepoQualifier.Remote) {
        val config = RealmConfiguration.Builder(schema = setOf(MbMenuDao::class)).build()
        RemoteMenusRepository(
            get(),
            LocalMenusRepository(get()),
            Realm.open(config)
        )
    }
}