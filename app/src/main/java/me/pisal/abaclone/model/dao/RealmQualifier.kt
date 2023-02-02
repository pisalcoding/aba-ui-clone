package me.pisal.abaclone.model.dao

import io.realm.kotlin.types.RealmObject
import org.koin.core.qualifier.Qualifier
import org.koin.ext.getFullName

sealed class RealmQualifier<T : RealmObject>(val schema: Class<T>) : Qualifier {
    object MbMenuQua : RealmQualifier<MbMenuDao>(MbMenuDao::class.java) {
        override val value: String = MbMenuDao::class.getFullName()
    }
}