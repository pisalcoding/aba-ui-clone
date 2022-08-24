package me.pisal.abaclone.ui.binding

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import me.pisal.abaclone.R

@BindingAdapter("menuDrawableRes", "menuIconUrl", requireAll = false)
fun ImageView.bindMenuImage(
    drawableRes: Int?,
    menuIconUrl: String?
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