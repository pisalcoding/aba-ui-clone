package me.pisal.abaclone.model

import java.io.Serializable

interface IDto<T>: Serializable {
    fun toDao(): T
}