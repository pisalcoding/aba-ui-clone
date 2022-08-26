package me.pisal.abaclone.common.custom.pinview

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import androidx.core.view.get
import me.pisal.abaclone.R
import me.pisal.abaclone.common.custom.keypad.SingleKeyView

/**
 * PIN View with numeric keypad
 */
class PinAuthView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defAttr: Int = 0,
) : LinearLayout(context, attributeSet, defAttr) {

    /**
     * Number of single PIN views to be displayed
     */
    var allowedPinsCount: Int = DEFAULT_PIN_COUNT
        set(value) {
            field = value
            layout()
        }

    private var _pins: ArrayList<Int> = arrayListOf()
    val pins: String
    get() {
        return _pins.joinToString("") { it.toString() }
    }

    val isFilled: Boolean = _pins.size == allowedPinsCount
    var onFilled: ((pins: String) -> Unit)? = null

    init {
        View.inflate(context, R.layout.pin_view_with_keypad, this)
        val attr = context.obtainStyledAttributes(attributeSet, R.styleable.PinAuthView, 0, 0)
        allowedPinsCount = attr.getInt(R.styleable.PinAuthView_pavPinCount, DEFAULT_PIN_COUNT)
        attr.recycle()
    }

    private fun getPinsContainer(): LinearLayout = findViewById(R.id.pins_container)

    fun layout() {
        val pinsContainer = getPinsContainer()
        pinsContainer.removeAllViews()
        for (i in 0 until allowedPinsCount) {
            val singlePin = SinglePinView(context)
            singlePin.tag = i
            pinsContainer.addView(singlePin)
        }
        initListeners()
    }

    fun clearInputs() {
        _pins = arrayListOf()
        layout()
    }

    private fun initListeners() {
        val key0 = findViewById<SingleKeyView>(R.id.key0)
        val key1 = findViewById<SingleKeyView>(R.id.key1)
        val key2 = findViewById<SingleKeyView>(R.id.key2)
        val key3 = findViewById<SingleKeyView>(R.id.key3)
        val key4 = findViewById<SingleKeyView>(R.id.key4)
        val key5 = findViewById<SingleKeyView>(R.id.key5)
        val key6 = findViewById<SingleKeyView>(R.id.key6)
        val key7 = findViewById<SingleKeyView>(R.id.key7)
        val key8 = findViewById<SingleKeyView>(R.id.key8)
        val key9 = findViewById<SingleKeyView>(R.id.key9)
        val keyBackspace = findViewById<View>(R.id.key_backspace)
        val keyClear = findViewById<View>(R.id.key_clear)

        listOf(key0, key1, key2, key3, key4, key5, key6, key7, key8, key9).forEach { key ->
            key.setOnClickListener {
                numKeyPressed(key)
            }
        }

        keyBackspace.setOnClickListener {
            backKeyPressed()
        }

        keyClear.setOnClickListener {
            clearKeyPressed()
        }
    }

    private fun numKeyPressed(key: SingleKeyView) {
        val pinsContainer = getPinsContainer()
        if (_pins.size == allowedPinsCount) {
            return
        }

        (pinsContainer[_pins.size] as SinglePinView).hasValue = true
        _pins.add(key.keyTitle.toInt())

        if (_pins.size == allowedPinsCount) {
            onFilled?.invoke(pins)
        }
    }

    private fun backKeyPressed() {
        val pinsContainer = getPinsContainer()
        if (_pins.size == 0) {
            return
        }

        (pinsContainer[_pins.size - 1] as SinglePinView).hasValue = false
        _pins.removeLast()
    }

    private fun clearKeyPressed() {
        clearInputs()
    }

    companion object {
        const val DEFAULT_PIN_COUNT = 4
    }
}