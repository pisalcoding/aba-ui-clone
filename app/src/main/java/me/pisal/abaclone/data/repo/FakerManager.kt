package me.pisal.abaclone.data.repo

import io.bloco.faker.Faker
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object FakerManager {

    suspend fun init() {
        withContext(Dispatchers.IO) {
            instance = Faker()
        }
    }

    suspend fun getInstance(): Faker {
        if (!::instance.isInitialized) {
            throw RuntimeException("Faker is not initialized! Make sure you called FakerManager.init() before using FakerManager.getInstance()")
        }
        return instance
    }

    private lateinit var instance: Faker

}