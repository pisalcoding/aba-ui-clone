package me.pisal.abaclone.data.repo.template

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import me.pisal.abaclone.common.TResult
import me.pisal.abaclone.data.repo.FakerManager
import me.pisal.abaclone.model.dto.ResponseWrapper
import me.pisal.abaclone.model.entity.TrxTemplate
import me.pisal.abaclone.model.enumeration.TemplateType

class TemplateRepository : ITemplateRepository {

    override suspend fun list(type: TemplateType): TResult<ResponseWrapper<List<TrxTemplate>>> {
        return withContext(Dispatchers.IO) {
            val list = arrayListOf<TrxTemplate>()
            val faker = FakerManager.getInstance()

            when (type) {
                TemplateType.TRANSFER -> {
                    for (i in 0 until 20) {
                        list.add(
                            TrxTemplate(
                                id = i,
                                title = faker.name.name(),
                                favorite = false,
                                serviceCode = "SFT001",
                                sourceAcc = "00053349$i",
                                destinationAcc = "01085511$i",
                                currency = "KHR"
                            )
                        )
                    }
                }

                TemplateType.PAYMENT -> {
                    for (i in 0 until 20) {
                        list.add(
                            TrxTemplate(
                                id = i,
                                title = faker.name.name(),
                                favorite = false,
                                serviceCode = "SFT001",
                                sourceAcc = "00053349$i",
                                destinationAcc = "01085511$i",
                                currency = "KHR"
                            )
                        )
                    }
                }
            }

            TResult.Success(ResponseWrapper((list)))
        }
    }
}