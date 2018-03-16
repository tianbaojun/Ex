package com.tabjin.ex.ui.adapter.ItemDecoration;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Tabjin on 2018/3/16/016.
 */

public class GridWhiteItemDecoration extends RecyclerView.ItemDecoration {

    private Drawable mDrawable;

    public void setDrawable(Drawable drawable) {
        mDrawable = drawable;
    }

    public GridWhiteItemDecoration(Drawable mDrawable) {
        this.mDrawable = mDrawable;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        int count = parent.getChildCount();
        GridLayoutManager layoutManager = (GridLayoutManager) parent.getLayoutManager();
        int orientation = layoutManager.getOrientation();
        int span = layoutManager.getSpanCount();
        for (int i = 0; i < count; i++) {
            boolean drawVertical = true;
            boolean drawHorizatal = true;

            //最后一行有几个
            int extra = count % span;
            extra = extra == 0 ? span : extra;
            switch (orientation) {
                case OrientationHelper.HORIZONTAL:
                    if ((i + 1) % span == 0) {
                        drawHorizatal = false;
                    }
                    if (i >= count - extra) {
                        drawVertical = false;
                    }
                    break;
                case OrientationHelper.VERTICAL:
                    if ((i + 1) % span == 0) {
                        drawVertical = false;
                    }
                    if (i >= count - extra) {
                        drawHorizatal = false;
                    }
                    break;
            }
            if (drawVertical) {
                drawVertical(c, parent, i);
            }
            if (drawHorizatal) {
                drawHorizatal(c,parent,i);
            }
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        GridLayoutManager manager = (GridLayoutManager) parent.getLayoutManager();
        int orientation = manager.getOrientation();
        int index = parent.getChildLayoutPosition(view);
        if (orientation == OrientationHelper.VERTICAL && (index + 1) % manager.getSpanCount() == 0) {
            outRect.set(0,0,0,mDrawable.getIntrinsicHeight());
            return;
        }
        if (orientation == OrientationHelper.HORIZONTAL && (index + 1) % manager.getSpanCount() == 0) {
            outRect.set(0,0,mDrawable.getIntrinsicWidth(),0);
            return;
        }
        outRect.set(0, 0, mDrawable.getIntrinsicWidth(), mDrawable.getIntrinsicHeight());
    }

    private void drawVertical(Canvas c, RecyclerView parent, int i) {
        View view = parent.getChildAt(i);
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
        int left = view.getRight() + layoutParams.rightMargin;
        int top = view.getTop() - layoutParams.topMargin;
        int right = left + mDrawable.getIntrinsicWidth();
        int bottom = view.getBottom() + layoutParams.bottomMargin;
        mDrawable.setBounds(left, top, right, bottom);
        mDrawable.draw(c);
    }

    private void drawHorizatal(Canvas c, RecyclerView parent, int i) {
        View view = parent.getChildAt(i);
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
        int left = view.getLeft() - layoutParams.leftMargin;
        int top = view.getBottom() + layoutParams.bottomMargin;
        int right = view.getRight() + layoutParams.rightMargin;
        int bottom = top+mDrawable.getIntrinsicHeight();
        mDrawable.setBounds(left, top, right, bottom);
        mDrawable.draw(c);
    }


}
