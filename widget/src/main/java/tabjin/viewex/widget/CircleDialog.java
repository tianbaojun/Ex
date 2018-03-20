package tabjin.viewex.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import tabjin.viewex.R;

/**
 * Created by Tabjin on 2018/3/8/008.
 * 转圈圈
 */

public class CircleDialog extends View {

    int firstColor;

    int secondColor;

    int speed;

    int circleWidth;

    int progress = 0;

    Paint paint;

    //圆圈颜色
    boolean isFirst = true;

    RectF rectF ;

    public CircleDialog(Context context) {
        this(context, null);
    }

    public CircleDialog(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleDialog(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CircleDialog, defStyleAttr, 0);
        for (int i = 0; i < typedArray.getIndexCount(); i++) {
            int attr = typedArray.getIndex(i);
            switch (attr) {
                case R.styleable.CircleDialog_circleWidth:
                    circleWidth = typedArray.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, 20, getResources().getDisplayMetrics()));
                    break;
                case R.styleable.CircleDialog_speed:
                    speed = typedArray.getInteger(attr, 20);
                    break;
                case R.styleable.CircleDialog_firstColor:
                    firstColor = typedArray.getColor(attr, Color.GRAY);
                    break;
                case R.styleable.CircleDialog_secondColor:
                    secondColor = typedArray.getColor(attr, Color.BLUE);
                    break;
            }
        }
        typedArray.recycle();

        paint = new Paint();
        paint.setColor(firstColor);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(circleWidth);
        paint.setAntiAlias(true);



        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    progress++;
                    if (progress == 361) {
                        progress = 0;
                        isFirst = !isFirst;
                    }
                    try {
                        Thread.sleep(speed);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    postInvalidate();
                }
            }
        });
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);

        if (rectF == null) {
            rectF = new RectF(getMeasuredWidth() / 2 - 40, getMeasuredHeight() / 2 - 40, getMeasuredWidth() / 2 + 40, getMeasuredHeight() / 2 + 40);
        }
        if (isFirst) {
            paint.setColor(secondColor);
            canvas.drawCircle(getMeasuredWidth() / 2, getMeasuredHeight() / 2, 40, paint);
            paint.setColor(firstColor);
            canvas.drawArc(rectF, -90, progress, false, paint);
        }else{
            paint.setColor(firstColor);
            canvas.drawCircle(getMeasuredWidth() / 2, getMeasuredHeight() / 2, 40, paint);
            paint.setColor(secondColor);
            canvas.drawArc(rectF, -90, progress, false, paint);
        }

    }
}
