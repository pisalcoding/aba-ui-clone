package me.pisal.abaclone.model

interface IDao<T> : Identifiable {
    fun toDto(): T

    fun updateFromDto(dto: T): IDao<T>
}