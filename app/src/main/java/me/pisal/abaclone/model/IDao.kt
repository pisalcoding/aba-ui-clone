package me.pisal.abaclone.model

interface IDao<T> {
    fun toDto(): T

    fun updateFromDto(dto: T): IDao<T>
}