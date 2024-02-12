package me.pisal.abaclone.data.repo.menu

import io.bloco.faker.Faker
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import me.pisal.abaclone.R
import me.pisal.abaclone.common.TResult
import me.pisal.abaclone.data.repo.FakerManager
import me.pisal.abaclone.model.MenuType
import me.pisal.abaclone.model.dto.MbMenuDto
import me.pisal.abaclone.model.dto.ResponseWrapper

class LocalMenusRepository() : MenusRepository {
    override suspend fun homeMenus(useCache: Boolean):
            TResult<ResponseWrapper<List<MbMenuDto>>> {
        return TResult.Success(ResponseWrapper(listOf()))
    }

    override suspend fun transferChannelMenus(useCache: Boolean):
            TResult<ResponseWrapper<List<MbMenuDto>>> {
        return withContext(Dispatchers.IO) {
            val faker = FakerManager.getInstance()
            val list = arrayListOf<MbMenuDto>()
            list.add(
                MbMenuDto(
                    "MBFT999",
                    "Choose from Favorite",
                    MenuType.TRANSFER.name,
                    localDrawableId = R.drawable.ic_payment_templates,
                    serviceCode = "MBFT999",
                ),
            )
            for (i in 0 until 10) {
                list.add(
                    MbMenuDto(
                        id = "MBFT%03d".format(i),
                        title = faker.book.title(),
                        type = MenuType.PAYMENT.name,
                        localDrawableId = R.drawable.ic_payment_templates,
                        subtitle = faker.lorem.sentence(),
                        serviceCode = "MBFT%03d".format(i),
                    )
                )
            }
            TResult.Success(ResponseWrapper(list))
        }
    }

    override suspend fun paymentChannelMenus(useCache: Boolean):
            TResult<ResponseWrapper<List<MbMenuDto>>> {
        return withContext(Dispatchers.IO) {
            val faker = FakerManager.getInstance()
            val list = arrayListOf<MbMenuDto>()
            list.add(
                MbMenuDto(
                    "MBBP999",
                    "Choose from Favorite",
                    MenuType.TRANSFER.name,
                    localDrawableId = R.drawable.ic_payment_templates,
                    serviceCode = "MBBP999",
                ),
            )
            for (i in 0 until 10) {
                list.add(
                    MbMenuDto(
                        id = "MBBP%03d".format(i),
                        title = faker.book.title(),
                        type = MenuType.PAYMENT.name,
                        localDrawableId = R.drawable.ic_payment_templates,
                        subtitle = faker.lorem.sentence(),
                        serviceCode = "MBBP%03d".format(i),
                    )
                )
            }
            TResult.Success(ResponseWrapper(list))
        }
    }

    override suspend fun newAccountMenus(useCache: Boolean):
            TResult<ResponseWrapper<List<MbMenuDto>>> {

        return withContext(Dispatchers.IO) {
            val faker = FakerManager.getInstance()
            val list = arrayListOf<MbMenuDto>()
            for (i in 0 until 6) {
                list.add(
                    MbMenuDto(
                        id = "MBAO%03d".format(i),
                        title = faker.book.title(),
                        type = MenuType.NEW_ACCOUNT.name,
                        localDrawableId = R.drawable.ic_payment_templates,
                        subtitle = faker.lorem.paragraph(),
                        serviceCode = "MBAO%03d".format(i),
                    )
                )
            }
            TResult.Success(ResponseWrapper(list))
        }
    }

    override suspend fun newLoanMenus(useCache: Boolean):
            TResult<ResponseWrapper<List<MbMenuDto>>> {
        return withContext(Dispatchers.IO) {
            val faker = FakerManager.getInstance()
            val list = arrayListOf<MbMenuDto>()
            for (i in 0 until 5) {
                list.add(
                    MbMenuDto(
                        id = "MBAO%03d".format(i),
                        title = faker.book.title(),
                        type = MenuType.NEW_ACCOUNT.name,
                        localDrawableId = R.drawable.ic_payment_templates,
                        subtitle = faker.lorem.paragraph(),
                        serviceCode = "MBAO%03d".format(i),
                    )
                )
            }
            TResult.Success(ResponseWrapper(list))
        }
    }

}