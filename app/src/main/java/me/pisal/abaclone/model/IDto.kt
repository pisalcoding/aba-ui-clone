package me.pisal.abaclone.model

import java.io.Serializable

interface IDto<out T> : Serializable, Identifiable {
    fun toDao(): T
}