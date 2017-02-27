package com.example.ccripplebutton;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;

/**
 * Created by henryzheng on 2017/2/27.
 */

public class RippleDrawable extends Drawable {
    private final Paint mPaint;
    private float touchX, touchY;
    Callback callback;
    int radius=0;
    private int width=0;
    private int height=0;
    public float mAlpha;
    private AnimatorSet set;
    int defaultAlpha=48;
    public RippleDrawable(Callback c) {
        mPaint = new Paint();
        mPaint.setColor(0x30000000);
        mPaint.setAlpha(defaultAlpha);
        callback =c;
        setCallback(callback);
        set=new AnimatorSet();
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawCircle(touchX, touchY, radius, mPaint);

    }

    @Override
    public void setAlpha(int i) {

    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {

    }

    @Override
    public int getOpacity() {
        return PixelFormat.UNKNOWN;
    }

    public void onTouchEvent(final MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            touchX = event.getX();
            touchY = event.getY();
            mPaint.setAlpha(defaultAlpha);
            final float mRadius= (float) Math.pow(Math.pow(width,2)+Math.pow(height,2),0.5);
            final ObjectAnimator anim1 = ObjectAnimator//
                    .ofFloat(this, "draw", 0F,  mRadius)//
                    .setDuration(500);//

            anim1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
            {
                @Override
                public void onAnimationUpdate(ValueAnimator animation)
                {
                    float changeValue= (float) animation.getAnimatedValue();
                    radius = (int) (changeValue);
                    invalidateSelf();
            }
            });
            anim1.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {

                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    mAlpha= 0;
                    mPaint.setAlpha((int) mAlpha);
                    invalidateSelf();
                }

                @Override
                public void onAnimationCancel(Animator animation) {
                    radius =0;
                    invalidateSelf();
                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });
            set.play(anim1);
            if (!set.isStarted())
                set.cancel();
            set.start();

        }
    }

    public void setWH(int width, int height) {
        this.width=width;
        this.height=height;
    }
}
