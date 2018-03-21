package com.tabjin.zhy.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.tabjin.zhy.R;

/**
 * Created by tabjin on 18-3-21.
 * 横向滑动出现删除按钮
 */

public class ScrollActionListView extends ListView {

    private Button delBtn;

    private PopupWindow popupWindow;

    private int touchSlop;


    public ScrollActionListView(Context context) {
        this(context,null);
    }

    public ScrollActionListView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ScrollActionListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view = LayoutInflater.from(context).inflate(R.layout.del_btn, null);
        delBtn = view.findViewById(R.id.id_item_btn);
        popupWindow = new PopupWindow(view, LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        touchSlop = ViewConfiguration.get(context).getScaledTouchSlop();

    }

    private int currentPosition;

    private View currentView;

    private int xDown,yDown;

    private boolean isSliding;
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        int action = ev.getAction();

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                xDown = (int) ev.getX();
                yDown = (int) ev.getY();
                if (popupWindow.isShowing()) {
                    popupWindow.dismiss();
                }
                currentPosition = pointToPosition(xDown, yDown);
                currentView = getChildAt(currentPosition - getFirstVisiblePosition());
                break;
                case MotionEvent.ACTION_MOVE:
                    int xMove = (int) ev.getX();
                    int yMove = (int) ev.getY();
                    if(xMove<xDown&&(yMove-yDown)<touchSlop&&xMove-xDown>touchSlop){
                        isSliding = true;
                    }
                    break;

        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        if (isSliding) {
            switch (action) {
                case MotionEvent.ACTION_MOVE:
                    int[] point = new int[2];
                    currentView.getLocationOnScreen(point);
                    popupWindow.update();
                    popupWindow.showAtLocation(currentView, Gravity.LEFT|Gravity.TOP,
                            point[0]+currentView.getWidth(),
                            point[1]+currentView.getHeight()/2-popupWindow.getContentView().getHeight()/2);
                    break;
                case MotionEvent.ACTION_UP:
                    isSliding = false;
                    break;
            }
            return true;
        }
        return super.onTouchEvent(ev);
    }
}
