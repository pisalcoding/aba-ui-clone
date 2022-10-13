package me.pisal.abaclone.module

import org.koin.core.qualifier.Qualifier

sealed class RepoQualifier : Qualifier {
    object Local : RepoQualifier() {
        override val value = "Local"
    }

    object Remote : RepoQualifier() {
        override val value = "Remote"
    }
}
