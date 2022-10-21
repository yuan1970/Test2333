package com.example.test233

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration

/**
 * @author anyu
 * @date 2022/5/19-9:44
 * @desc
 */
class RvDividerItemDecorationUtil(context: Context, px: Int, color: Int) : ItemDecoration() {

    private var mDividerHeight = 0f

    private lateinit var mPaint: Paint

    private var mDx = 0

  init {
      mDx = px
      mPaint = Paint()
      mPaint.isAntiAlias=true
      mPaint.setColor(ContextCompat.getColor(context, color))
  }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        //如果不是第一个，则设置top的值。
        if (parent.getChildAdapterPosition(view) != 0) {
            outRect.top = mDx
            mDividerHeight = mDx.toFloat()
        }
    }

    override fun onDraw(
        c: Canvas,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.onDraw(c, parent, state)
        val childCount = parent.childCount
        for (i in 0 until childCount) {
            val view = parent.getChildAt(i)
            val index = parent.getChildAdapterPosition(view)
            //第一个ItemView不需要绘制
            if (index == 0) {
                continue
            }
            val dividerTop = view.top - mDividerHeight
            val dividerLeft = parent.paddingLeft.toFloat()
            val dividerBottom = view.top.toFloat()
            val dividerRight = (parent.width - parent.paddingRight).toFloat()
            c.drawRect(dividerLeft, dividerTop, dividerRight, dividerBottom, mPaint!!)
        }
    }


}