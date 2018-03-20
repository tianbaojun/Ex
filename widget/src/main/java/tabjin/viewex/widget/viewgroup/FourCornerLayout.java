package tabjin.viewex.widget.viewgroup;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Tabjin on 2018/3/19/019.
 * 四角各有一个view
 */

public class FourCornerLayout extends ViewGroup {
    public FourCornerLayout(Context context) {
        super(context);
    }

    public FourCornerLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FourCornerLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet p) {
        return new MarginLayoutParams(getContext(), p);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        //测量子view
        measureChildren(widthMeasureSpec,heightMeasureSpec);
        int widthModel = MeasureSpec.getMode(widthMeasureSpec);
        int heightModel = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int tWidth = 0;
        int bWidth = 0;
        int lHeight = 0;
        int rHeight = 0;

        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View childView = getChildAt(i);
            int cWidth = childView.getMeasuredWidth();
            int cHeight = childView.getMeasuredHeight();
            MarginLayoutParams cParams = (MarginLayoutParams) childView.getLayoutParams();

            if (i == 0 || i == 1) {
                tWidth += cWidth+cParams.leftMargin+cParams.rightMargin;
            }

            if (i == 2 || i == 3) {
                bWidth += cWidth+cParams.leftMargin+cParams.rightMargin;
            }

            if (i == 0 || i == 2) {
                lHeight += cHeight+cParams.topMargin+cParams.bottomMargin;
            }

            if (i == 1 || i == 3) {
                rHeight += cHeight+cParams.topMargin+cParams.bottomMargin;
            }

        }

        int actWidht = Math.max(tWidth,bWidth);
        int actHeight = Math.max(lHeight,rHeight);

        setMeasuredDimension(widthModel==MeasureSpec.EXACTLY?widthSize:actWidht,heightModel==MeasureSpec.EXACTLY?heightSize:actHeight);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {


        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            MarginLayoutParams layoutParams = (MarginLayoutParams) child.getLayoutParams();
            int cl = 0,ct = 0,cr,cb;
            switch (i) {
                case 0:
                    cl = layoutParams.leftMargin;
                    ct = layoutParams.topMargin;
                    break;
                case 1:
                    cl = getWidth()-layoutParams.rightMargin-child.getMeasuredWidth();
                    ct = layoutParams.topMargin;
                    break;
                case 2:
                    cl = layoutParams.leftMargin;
                    ct = getHeight()-layoutParams.bottomMargin-child.getMeasuredHeight();
                    break;
                case 3:
                    cl = getWidth()-child.getMeasuredWidth()-layoutParams.rightMargin;
                    ct = getHeight()-child.getMeasuredHeight()-layoutParams.bottomMargin;
                    break;
            }

            cr = cl+child.getMeasuredWidth();
            cb = ct+child.getMeasuredHeight();
            child.layout(cl, ct, cr, cb);
        }
    }
}
