package com.example.scelablecapital.presentation.commits

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View

class ChartView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val paint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val rect: Rect = Rect()
    private var strokeColor: Int = Color.BLUE
    private var percent = 0

    init {
        paint.color = strokeColor
    }

    fun setPercent(percent: Int) {
        if (percent != this.percent) {
            this.percent = percent
            invalidate()
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        rect.left = 0;
        rect.right = width;
        rect.top = height - ((height.toDouble() / HUNDRED_PERCENT) * percent).toInt()
        rect.bottom = height

        canvas.drawRect(rect, paint);
    }

    companion object {
        private const val HUNDRED_PERCENT = 100.0
    }
}