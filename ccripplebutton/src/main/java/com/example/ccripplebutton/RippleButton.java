package com.example.ccripplebutton;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Button;

import com.example.commonlibrary.utils.CCLog;

/**
 * Created by henryzheng on 2017/2/27.
 */

public class RippleButton extends Button {
    private final RippleDrawable rippleDrawable;

    public RippleButton(Context context) {
        this(context, null);
        CCLog.v("RippleButton(Context context)");
    }

    public RippleButton(Context context, AttributeSet attrs) {
        this(context, attrs,0);
        CCLog.v("RippleButton(Context context, AttributeSet attrs)");

    }

    public RippleButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs,defStyleAttr);
        CCLog.v(" RippleButton(Context context, AttributeSet attrs, int defStyleAttr)");
         rippleDrawable=new RippleDrawable(this);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        rippleDrawable.draw(canvas);

        super.onDraw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        rippleDrawable.onTouchEvent(event);
//        invalidate();
        return super.onTouchEvent(event);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        rippleDrawable.setWH(getWidth(),getHeight());
    }

    @Override
    protected boolean verifyDrawable(Drawable who) {

        return who==rippleDrawable||super.verifyDrawable(who);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        rippleDrawable.setBounds(0,0,getWidth(),getHeight());
    }
}
