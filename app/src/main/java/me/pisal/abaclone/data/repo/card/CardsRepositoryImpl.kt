package me.pisal.abaclone.data.repo.card

import me.pisal.abaclone.common.TResult
import me.pisal.abaclone.model.dto.ResponseWrapper
import me.pisal.abaclone.model.entity.Card

class CardsRepositoryImpl : CardsRepository {
    override suspend fun myCards(): TResult<ResponseWrapper<List<Card>>> {
        TODO("Not yet implemented")
    }

    override suspend fun create(dto: Card): TResult<ResponseWrapper<Card>> {
        TODO("Not yet implemented")
    }
}