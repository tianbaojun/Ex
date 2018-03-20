package tabjin.viewex.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

import tabjin.viewex.R;

/**
 * Created by Tabjin on 2018/3/8/008.
 * 有北京图片的TextView
 */

public class ImageTextView extends View {

    String text;

    int textColor;

    int textSize;

    Bitmap image;

    int imageScaleType;

    //文字边界
    Rect mTextBounds;

    //文字画笔
    Paint paint;

    //图片边界
    Rect rect;

    public ImageTextView(Context context) {
        this(context, null);
    }

    public ImageTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ImageTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.ImageTextView, defStyleAttr, 0);
        for(int i=0;i<typedArray.getIndexCount();i++) {
            int attr = typedArray.getIndex(i);
            switch (attr) {
                case R.styleable.ImageTextView_text:
                    text = typedArray.getString(attr);
                    break;
                case R.styleable.ImageTextView_textColor:
                    textColor = typedArray.getColor(attr, Color.BLACK);
                    break;
                case R.styleable.ImageTextView_textSize:
                    textSize = typedArray.getDimensionPixelSize(attr,
                            (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,14,getResources().getDisplayMetrics()));
                    break;
                case R.styleable.ImageTextView_image:
                    image = BitmapFactory.decodeResource(getResources(), typedArray.getResourceId(attr, 0));
                    break;
                case R.styleable.ImageTextView_imageScaleType:
                    imageScaleType = typedArray.getInt(attr, 0);
                    break;
            }
        }

        typedArray.recycle();

        paint = new Paint();
        rect = new Rect();
        mTextBounds = new Rect();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(widthMeasureSpec);

        paint.setTextSize(textSize);
        paint.getTextBounds(text,0,text.length(),mTextBounds);
        if (widthMode != MeasureSpec.EXACTLY) {
            int imgWidth = getPaddingLeft()+getPaddingRight()+image.getWidth();
            int textWidth = getPaddingLeft()+getPaddingRight()+mTextBounds.width();
            if(widthMode == MeasureSpec.AT_MOST){
                int width = Math.max(imgWidth, textWidth);
                widthSize = Math.min(width, widthSize);
            }
        }
        if (heightMode != MeasureSpec.EXACTLY) {
            if(heightMode == MeasureSpec.AT_MOST){
                int width = getPaddingBottom()+getPaddingTop()+mTextBounds.height()+image.getHeight();
                heightSize = Math.min(width, heightSize);
            }
        }

        setMeasuredDimension(widthSize,heightSize);
        Log.e("tabjin", "widthsize=" + widthSize + "heightSize=" + heightSize);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        paint.setColor(Color.RED);
        paint.setStrokeWidth(4);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), paint);

        paint.setColor(textColor);
        paint.setStyle(Paint.Style.FILL);
        if (mTextBounds.width() > getMeasuredWidth()) {
            TextPaint textPaint = new TextPaint(paint);
            String msg = TextUtils.ellipsize(text,textPaint,getMeasuredWidth()-getPaddingLeft()-getPaddingRight(),TextUtils.TruncateAt.END).toString();
            canvas.drawText(msg,getPaddingLeft(),getMeasuredHeight()-getPaddingBottom(),paint);
        }else{
            canvas.drawText(text, getMeasuredWidth() / 2 - mTextBounds.width() / 2, getMeasuredHeight() - getPaddingBottom(), paint);
        }

        rect.left = getPaddingLeft();
        rect.right = getMeasuredWidth()-getPaddingRight();
        rect.top = getPaddingTop();
        rect.bottom = getMeasuredHeight()-getPaddingBottom()-mTextBounds.height();
        if (imageScaleType == 0) {
            canvas.drawBitmap(image, null, rect, paint);
        }else{
            rect.left = getMeasuredWidth()/2-image.getWidth()/2;
            rect.right = getMeasuredWidth()/2+image.getWidth()/2;
            rect.top = (getMeasuredHeight()-mTextBounds.height())/2-image.getHeight()/2;
            rect.left = (getMeasuredHeight()-mTextBounds.height())/2+image.getWidth()/2;

            canvas.drawBitmap(image,null,rect,paint);
        }
    }
}
