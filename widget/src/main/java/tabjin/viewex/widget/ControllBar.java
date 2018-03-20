package tabjin.viewex.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.Scroller;

import tabjin.viewex.R;

/**
 * Created by Tabjin on 2018/3/8/008.
 *
 * 调节音量显示控件
 */

public class ControllBar extends View {

    int firstColor;

    int secondColor;

    Bitmap image;

    int num;

    float splitSize;

    Paint paint;

    RectF rect;

    float itemSize;

    int circleWidth;

    //要显示的音量
    private int size = 0;

    //不显示东西的圆弧角度
    private float openSize = 0f;

    private Scroller scroller;

    public ControllBar(Context context) {
        this(context,null);
    }

    public ControllBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ControllBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.ControllBar, defStyleAttr, 0);
        for(int i = 0;i<typedArray.getIndexCount();i++) {
            int attr = typedArray.getIndex(i);
            switch (attr) {
                case R.styleable.ControllBar_firstColor:
                    firstColor = typedArray.getColor(attr, Color.BLACK);
                    break;
                case R.styleable.ControllBar_secondColor:
                    secondColor = typedArray.getColor(attr, Color.GRAY);
                    break;
                case R.styleable.ControllBar_image:
                    image = BitmapFactory.decodeResource(getResources(), typedArray.getResourceId(attr, 0));
                    break;
                case R.styleable.ControllBar_num:
                    num = typedArray.getInt(attr, 0);
                    break;
                case R.styleable.ControllBar_splitSize:
                    splitSize = typedArray.getFloat(attr, 0);
                    break;
                case R.styleable.ControllBar_circleWidth:
                    circleWidth = typedArray.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX,5,getResources().getDisplayMetrics()));
                    break;
                case R.styleable.ControllBar_openSize:
                    openSize = typedArray.getFloat(attr, 0);
                    break;
            }
        }
        typedArray.recycle();

        paint = new Paint();

        itemSize = (360f-openSize)/num-splitSize;

        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (size == num) {
                    size=0;
                }else{
                    size++;
                }
                drawArc(size);
                smoothScrollTo(20*size,10*size);
            }
        });


        scroller = new Scroller(getContext());
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
        if (rect == null) {
            rect = new RectF(circleWidth/2, circleWidth/2, getMeasuredWidth()-circleWidth/2, getMeasuredWidth()-circleWidth/2);
        }
        paint.setColor(firstColor);
        paint.setStrokeWidth(circleWidth);
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        paint.setStrokeCap(Paint.Cap.ROUND);
        for(int i=size;i<num;i++) {
            canvas.drawArc(rect, i * (itemSize + splitSize)+splitSize/2+(90+openSize/2), itemSize, false, paint);
        }
        paint.setColor(secondColor);
        for(int i = 0;i<size;i++) {
            canvas.drawArc(rect,i*(itemSize+splitSize)+splitSize/2+(90+openSize/2),itemSize,false,paint);
        }
    }

    public void drawArc(int size) {
        if (size < 0 || size > num) {
            return;
        }else{
            postInvalidate();
        }
    }

    @Override
    public void computeScroll() {
        if (scroller.computeScrollOffset()) {
            scrollTo(scroller.getCurrX(),scroller.getCurrY());
            postInvalidate();
        }
    }

    public void smoothScrollTo(int destX, int destY) {
        int scrollX = getScrollX();
        int delta = destX-scrollX;
        int deltaY = destY-getScrollY();
        scroller.startScroll(scrollX, getScrollY(), delta, deltaY, 1000);
        invalidate();
    }
}
