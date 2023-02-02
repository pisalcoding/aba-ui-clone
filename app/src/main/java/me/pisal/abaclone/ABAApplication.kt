package me.pisal.abaclone

import android.app.Application
import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.Registry
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.module.AppGlideModule
import com.bumptech.glide.request.RequestOptions
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import me.pisal.abaclone.model.dao.MbMenuDao
import me.pisal.abaclone.module.*
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import java.io.InputStream

class ABAApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
        initRealm()
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@ABAApplication)
            modules(listOf(apiModule, remoteDataModule, persistenceModule, viewModelModule))
        }
    }

    private fun initRealm() {
        val config = RealmConfiguration.Builder(schema = setOf(MbMenuDao::class))
            .build()
        val realm: Realm = Realm.open(config)
    }
}

@GlideModule
class MyAppGlideModule : AppGlideModule() {
    override fun registerComponents(context: Context, glide: Glide, registry: Registry) {
        httpClient().let {
            registry.replace(
                GlideUrl::class.java,
                InputStream::class.java,
                OkHttpUrlLoader.Factory(
                    it
                )
            )
        }
    }

    override fun applyOptions(context: Context, builder: GlideBuilder) {
        builder.setDefaultRequestOptions(
            RequestOptions().format(DecodeFormat.PREFER_RGB_565)
        )
    }

    private fun httpClient() = OkHttpClient()
}

