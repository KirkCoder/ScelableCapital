package com.example.scalablecapital.presentation.allrepositories

import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.example.scalablecapital.R

class AllRepositoriesItemDecorator(
    private val marginBottom: Int,
    private val sideMargin: Int,
    private val divider: Drawable,
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        with(outRect) {
            val count = parent.adapter?.itemCount ?: 0
            if (parent.getChildAdapterPosition(view) == count - 1) {
                bottom = marginBottom
            }
        }
    }

    override fun onDrawOver(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        for (i in 0 until parent.childCount - 1) {
            val child = parent.getChildAt(i)

            child.findViewById<AppCompatTextView>(R.id.nameTextView)?.let {
                val top = child.bottom
                val bottom = top + divider.intrinsicHeight
                val left = it.left
                val right = parent.width - sideMargin
                divider.setBounds(left, top, right, bottom)
                divider.draw(canvas)
            }
        }
    }
}