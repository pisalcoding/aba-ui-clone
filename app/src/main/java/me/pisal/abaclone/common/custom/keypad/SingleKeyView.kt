package me.pisal.abaclone.common.custom.keypad

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import me.pisal.abaclone.R


class SingleKeyView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defAttr: Int = 0
) : LinearLayout(context, attributeSet, defAttr) {

    var keyTitle: String = ""

    init {
        View.inflate(context, R.layout.single_key_pin, this)
        val attr = context.obtainStyledAttributes(attributeSet, R.styleable.SingleKeyView, 0, 0)
        keyTitle = attr.getString(R.styleable.SingleKeyView_skKey) ?: ""
        attr.recycle()

        findViewById<Button>(R.id.button).apply {
            text = keyTitle
            setOnClickListener {
                this@SingleKeyView.performClick()
            }
        }
    }
}