package me.pisal.abaclone.common.custom.pinview

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import androidx.core.view.isVisible
import me.pisal.abaclone.R

class SinglePinView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyle: Int = 0
): LinearLayout(context, attributeSet, defStyle){

    var hasValue: Boolean = false
    set(value) {
        field = value
        updateValue()
    }

    init {
        View.inflate(context, R.layout.single_pin_view, this)
        val attr = context.obtainStyledAttributes(defStyle, R.styleable.SinglePinView)
        hasValue = attr.getBoolean(R.styleable.SinglePinView_spvHasValue, false)
        attr.recycle()
    }

    private fun updateValue() {
        findViewById<View>(R.id.inner_dot).isVisible = hasValue
    }
}