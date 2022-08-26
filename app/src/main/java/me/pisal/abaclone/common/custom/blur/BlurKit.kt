@file:Suppress("DEPRECATION")

package me.pisal.abaclone.common.custom.blur

/**
 * Source: from stack overflow
 */
import android.content.Context
import android.graphics.Bitmap
import android.renderscript.Allocation
import android.renderscript.Element
import android.renderscript.RenderScript
import android.renderscript.ScriptIntrinsicBlur

class BlurKit {
    private var rs: RenderScript? = null
    fun blur(src: Bitmap, radius: Int): Bitmap {
        val input = Allocation.createFromBitmap(rs, src)
        val output = Allocation.createTyped(rs, input.type)
        val script: ScriptIntrinsicBlur = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs))
        script.setRadius(radius.toFloat())
        script.setInput(input)
        script.forEach(output)
        output.copyTo(src)
        return src
    }

    companion object {
        private var instance: BlurKit? = null
        fun init(context: Context?) {
            if (instance != null) {
                return
            }
            instance = BlurKit()
            instance!!.rs = RenderScript.create(context)
        }

        fun getInstance(): BlurKit? {
            if (instance == null) {
                throw RuntimeException("BlurKit not initialized!")
            }
            return instance
        }
    }
}