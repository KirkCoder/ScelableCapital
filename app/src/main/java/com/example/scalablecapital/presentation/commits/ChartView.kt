package com.example.scalablecapital.presentation.commits

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator

class ChartView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val paint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val rect: Rect = Rect()
    private var chartColor: Int = Color.BLUE
    private val valueAnimator = ValueAnimator().apply {
        interpolator = AccelerateDecelerateInterpolator()
        addUpdateListener { animator ->
            percent = animator.animatedValue as Int
            invalidate()
        }
    }

    private var percent = 0

    init {
        paint.color = chartColor
    }

    fun setPercent(percent: Int) {
        valueAnimator.cancel()
        this.percent = percent
    }

    fun changePercent(percent: Int) {
        valueAnimator.cancel()
        valueAnimator.setIntValues(this.percent, percent)
        valueAnimator.start()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        rect.left = 0
        rect.right = width
        rect.top = height - ((height.toDouble() / HUNDRED_PERCENT) * percent).toInt()
        rect.bottom = height

        canvas.drawRect(rect, paint)
    }

    companion object {
        private const val HUNDRED_PERCENT = 100.0
    }
}