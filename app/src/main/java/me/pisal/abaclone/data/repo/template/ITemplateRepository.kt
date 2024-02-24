package me.pisal.abaclone.data.repo.template

import me.pisal.abaclone.common.TResult
import me.pisal.abaclone.model.dto.ResponseWrapper
import me.pisal.abaclone.model.entity.TrxTemplate
import me.pisal.abaclone.model.enumeration.TemplateType

interface ITemplateRepository {
    suspend fun list(type: TemplateType): TResult<ResponseWrapper<List<TrxTemplate>>>
}
