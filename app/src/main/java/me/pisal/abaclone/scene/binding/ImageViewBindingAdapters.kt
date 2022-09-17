package me.pisal.abaclone.scene.binding

import android.content.Context
import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import me.pisal.abaclone.R

@BindingAdapter("menuDrawableRes", "menuIconUrl", requireAll = false)
fun ImageView.bindMenuImage(
    drawableRes: Int?,
    menuIconUrl: String?,
) {
    val placeholder =
        ResourcesCompat.getDrawable(resources, R.drawable.ic_accounts_home_menu, context.theme)
    val urlString = menuIconUrl?.trim()?.replace(" ", "%20") ?: ""
    if (urlString.isNotEmpty()) {
        var request = Glide.with(this).load(urlString)
        if (placeholder != null) request = request.placeholder(placeholder)
        request.into(this)
    } else if (drawableRes != null) {
        val local = ResourcesCompat.getDrawable(resources, drawableRes, context.theme)
        this.setImageDrawable(local)
    } else {
        this.setImageDrawable(placeholder)
    }
}

/**
 * If
 * @param drawableUri starts with "http", use Glide to load image,
 * Else
 *  - if drawableUri starts with "ic_" || "img_", try to load local drawable by name
 */
@BindingAdapter("drawableUri")
fun ImageView.bindDrawableUri(
    drawableUri: String?,
) {
    val uri = drawableUri ?: return
    if (uri.startsWith("http")) {
        val urlString = uri.trim().replace(" ", "%20")
        val request = Glide.with(this@bindDrawableUri).load(urlString)
        request.into(this@bindDrawableUri)
    } else {
        if (uri.startsWith("ic_") || uri.startsWith("img_")) {
            getDrawableByName(uri, context)?.let {
                this@bindDrawableUri.setImageDrawable(it)
            }
        }
    }
}

private fun getDrawableByName(name: String, context: Context): Drawable? {
    val resources: Resources = context.resources
    val resourceId: Int = resources.getIdentifier(name, "drawable",
        context.packageName)
    return ResourcesCompat.getDrawable(context.resources, resourceId, context.theme)
}
