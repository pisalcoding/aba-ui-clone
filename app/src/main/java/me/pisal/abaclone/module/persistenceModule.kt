package me.pisal.abaclone.module

import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import me.pisal.abaclone.data.repo.menu.LocalMenusRepository
import me.pisal.abaclone.data.repo.menu.MenusRepository
import me.pisal.abaclone.model.dao.MbMenuDao
import org.koin.dsl.module

val persistenceModule = module {
    single<MenusRepository>(qualifier = RepoQualifier.Local) {
        val config = RealmConfiguration.Builder(schema = setOf(MbMenuDao::class)).build()
        LocalMenusRepository(Realm.open(config))
    }
}