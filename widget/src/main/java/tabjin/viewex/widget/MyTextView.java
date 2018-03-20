package tabjin.viewex.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import tabjin.viewex.R;

/**
 * Created by Tabjin on 2018/3/5/005.
 * 自定义的TextView
 */

public class MyTextView extends View {

    private String text;

    private int textColor;

    private int textSize;

    private Rect mBound;

    private Paint mPaint;

    public MyTextView(Context context) {
        this(context,null);
    }

    public MyTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.MyTextView, defStyleAttr, 0);
        if (null != typedArray) {
            for(int i=0;i<typedArray.getIndexCount();i++) {
                int attr = typedArray.getIndex(i);
                switch (attr) {
                    case R.styleable.MyTextView_text:
                        text = typedArray.getString(attr);
                        break;
                    case R.styleable.MyTextView_textColor:
                        textColor = typedArray.getColor(attr, Color.BLACK);
                        break;
                    case R.styleable.MyTextView_textSize:
                        textSize = typedArray.getDimensionPixelSize(attr,
                                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,14,getResources().getDisplayMetrics()));
                        break;
                }
            }
            typedArray.recycle();
        }

        mBound = new Rect();
        mPaint = new Paint();
        mPaint.setTextSize(textSize);
        mPaint.getTextBounds(text,0,text.length(),mBound);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        if (widthMode != MeasureSpec.EXACTLY) {
            widthSize = getPaddingLeft()+getPaddingRight()+mBound.width();
        }
        if(heightMode != MeasureSpec.EXACTLY){
            heightSize = getPaddingTop()+getPaddingBottom()+mBound.height();
        }
        setMeasuredDimension(widthSize,heightSize);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(Color.YELLOW);
        canvas.drawRect(0,0,getMeasuredWidth(),getMeasuredHeight(),mPaint);
        mPaint.setColor(textColor);
        canvas.drawText(text,getWidth()/2-mBound.width()/2,getHeight()/2+mBound.height()/2,mPaint);
    }
}
