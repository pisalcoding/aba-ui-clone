package me.pisal.abaclone.data.repo.card

import me.pisal.abaclone.common.TResult
import me.pisal.abaclone.model.dto.ResponseWrapper
import me.pisal.abaclone.model.entity.Card

interface CardsRepository {
    suspend fun myCards(): TResult<ResponseWrapper<List<Card>>>
    suspend fun create(dto: Card): TResult<ResponseWrapper<Card>>
}