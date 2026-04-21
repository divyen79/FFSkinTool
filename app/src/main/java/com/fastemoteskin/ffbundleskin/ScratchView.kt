package com.fastemoteskin.ffbundleskin

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class ScratchView(context: Context, attrs: AttributeSet) : View(context, attrs) {

    private val paint = Paint()
    private lateinit var bitmap: Bitmap
    private lateinit var canvasBitmap: Canvas

    private var revealListener: (() -> Unit)? = null
    private var isRevealed = false

    init {
        paint.isAntiAlias = true
        paint.strokeWidth = 70f // 🔥 smooth scratch
        paint.style = Paint.Style.STROKE
        paint.strokeJoin = Paint.Join.ROUND
        paint.strokeCap = Paint.Cap.ROUND
        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
        canvasBitmap = Canvas(bitmap)

        // ✅ IMPORTANT: CLEAR CANVAS FIRST
        canvasBitmap.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR)

        // ✅ DRAW SCRATCH TOP LAYER (NOT BACKGROUND)
        val drawable = context.getDrawable(R.drawable.scratch_card_bg)
        drawable?.setBounds(0, 0, w, h)
        drawable?.draw(canvasBitmap)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawBitmap(bitmap, 0f, 0f, null)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {

        if (event.action == MotionEvent.ACTION_MOVE) {
            canvasBitmap.drawCircle(event.x, event.y, 50f, paint)
            invalidate()

            if (!isRevealed) {
                val percent = getScratchedPercent()

                if (percent > 60) { // ✅ 60% scratch
                    isRevealed = true
                    revealListener?.invoke()
                }
            }
        }
        return true
    }

    // 🎯 detect scratched %
    private fun getScratchedPercent(): Int {
        val width = bitmap.width
        val height = bitmap.height

        val pixels = IntArray(width * height)
        bitmap.getPixels(pixels, 0, width, 0, 0, width, height)

        var transparent = 0

        for (pixel in pixels) {
            if (pixel == 0) transparent++
        }

        return (transparent * 100) / pixels.size
    }

    // 🎯 callback for activity
    fun setRevealListener(listener: () -> Unit) {
        this.revealListener = listener
    }
}