package me.pisal.abaclone.module

import me.pisal.abaclone.data.api.MenusApi
import me.pisal.abaclone.data.api.http.HttpClientFactory
import org.koin.dsl.module
import retrofit2.Retrofit

val apiModule = module {
    single { HttpClientFactory.instance }
    single { get<HttpClientFactory>().sslPinner() }
    single { get<HttpClientFactory>().logger() }
    single { get<HttpClientFactory>().httpClient(get(), get()) }
    single { get<HttpClientFactory>().retrofit(get()) }

    single { get<Retrofit>().create(MenusApi::class.java) }
}