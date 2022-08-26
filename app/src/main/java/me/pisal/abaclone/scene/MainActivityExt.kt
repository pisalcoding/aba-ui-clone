package me.pisal.abaclone.scene

import android.graphics.RenderEffect
import android.graphics.Shader
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.core.view.isGone
import me.pisal.abaclone.R
import me.pisal.abaclone.common.custom.blur.BlurLayout
import me.pisal.abaclone.common.extension.hide
import me.pisal.abaclone.common.extension.show

fun MainActivity.setNavigationBackgroundColor(@ColorRes colorResId: Int){
    window.navigationBarColor = resources.getColor(colorResId, theme)
}

fun MainActivity.showBlur() {
     if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
         findViewById<View>(R.id.app_bar_main)?.run {
             val blurEffect = RenderEffect.createBlurEffect(100f, 100f, Shader.TileMode.MIRROR)
             setRenderEffect(blurEffect)
         }
    } else {
        findViewById<BlurLayout>(R.id.blur_view)?.show()
    }
}

fun MainActivity.hideBlur() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        findViewById<View>(R.id.app_bar_main)?.run {
            setRenderEffect(null)
        }
    } else {
        findViewById<BlurLayout>(R.id.blur_view)?.show()
    }
}

fun MainActivity.hideBlurIfNotLoading() {
    if (findViewById<BlurLayout>(R.id.loading).isGone) {
        hideBlur()
    }
}

fun MainActivity.showLoading() {
    findViewById<BlurLayout>(R.id.loading)?.show()
}

fun MainActivity.hideLoading() {
    findViewById<BlurLayout>(R.id.loading)?.hide()
}

fun MainActivity.showActionBar() {
    supportActionBar?.show()
}

fun MainActivity.hideActionBar() {
    supportActionBar?.hide()
}

fun MainActivity.setToolbarTitle(title: String) {
    findViewById<TextView>(R.id.toolbar_title)?.run {
        text = title
    }
}

fun MainActivity.safeRunOnUiThread(after: Long, doSth: () -> Unit) {
    Handler(Looper.getMainLooper()).postDelayed({
        try {
            runOnUiThread {
                doSth()
            }
        } catch (t: Throwable) {
            t.printStackTrace()
        }
    }, after)
}

fun MainActivity.safeRunOnUiThread(doSth: () -> Unit) {
    Handler(Looper.getMainLooper()).post {
        try {
            runOnUiThread {
                doSth()
            }
        } catch (t: Throwable) {
            t.printStackTrace()
        }
    }
}