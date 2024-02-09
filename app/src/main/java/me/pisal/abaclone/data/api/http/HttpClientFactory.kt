package me.pisal.abaclone.data.api.http

import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class HttpClientFactory private constructor() {

    val baseUrl by lazy {
        "https://example.com/"
    }

    val baseApiUrl by lazy {
        baseUrl + "api/v1.0.0"
    }

    fun sslPinner(): CertificatePinner {
        return CertificatePinner.Builder().apply {
            add("sha256/deeLlGI/+po/examplemEPv9bre96kPf1FDbiFtaTwM=")
        }.build()
    }

    fun logger(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    fun httpClient(
        certificatePinner: CertificatePinner,
        logger: HttpLoggingInterceptor,
        encryptionInterceptor: EncryptionInterceptor,
    ): OkHttpClient {
        return OkHttpClient.Builder().apply {
            // certificatePinner(certificatePinner)
            // addInterceptor(encryptionInterceptor)
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

    companion object {
        val instance by lazy { HttpClientFactory() }
    }
}