package me.pisal.abaclone.data.api.http

import me.pisal.abaclone.BuildConfig
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class HttpClientFactory private constructor() {

    val baseUrl by lazy {
        BuildConfig.URL_SCHEME + "://" + BuildConfig.DOMAIN_NAME
    }

    val baseApiUrl by lazy {
        BuildConfig.URL_SCHEME + "://" + BuildConfig.DOMAIN_NAME + BuildConfig.BASE_API_PATH
    }

    fun sslPinner(): CertificatePinner{
        return CertificatePinner.Builder().apply {
            val hashes = BuildConfig.SERVER_HASHES.split(",")
            hashes.forEach { add(BuildConfig.DOMAIN_NAME, it) }
        }.build()
    }

    fun logger(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            if (BuildConfig.DEBUG) {
                level = HttpLoggingInterceptor.Level.BODY
            } else {
                level = HttpLoggingInterceptor.Level.NONE
            }
        }
    }

    fun httpClient(
        certificatePinner: CertificatePinner,
        logger: HttpLoggingInterceptor,
    ): OkHttpClient {
        return OkHttpClient.Builder().apply {
//            certificatePinner(certificatePinner)
            addInterceptor(logger)
        }.build()
    }

    fun retrofit(
        httpClient: OkHttpClient,
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseApiUrl)
            .client(httpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    fun <T> getService(
        retrofit: Retrofit,
        serviceClass: Class<T>,
    ): T {
        return retrofit.create(serviceClass)
    }

    companion object {
        val instance by lazy { HttpClientFactory() }
    }
}