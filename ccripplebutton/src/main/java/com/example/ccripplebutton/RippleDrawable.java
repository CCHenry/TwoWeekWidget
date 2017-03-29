package com.example.ccripplebutton;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by henryzheng on 2017/2/27.
 */

public class RippleDrawable extends Drawable {
    private float touchX, touchY;
    Callback callback;
    private int width = 0;
    private int height = 0;
    public float mAlpha;
    private AnimatorSet set;
    int defaultAlpha = 48;
    List<ChangeData> changeDatas = new ArrayList<>();
    private Paint paint;
    private int rediu = 0;

    public RippleDrawable(Callback c) {
//        mPaint = new Paint();
//        mPaint.setColor(0x30000000);
//        mPaint.setAlpha(defaultAlpha);
        callback = c;
        setCallback(callback);
        set = new AnimatorSet();
    }

    @Override
    public void draw(Canvas canvas) {
        for (int i = 0; i < changeDatas.size(); i++) {
            canvas.drawCircle(changeDatas.get(i).getTouchX(), changeDatas.get(i).getTouchY(),
                    changeDatas.get(i).getRedic(), changeDatas.get(i).getPaint());
        }
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
            final Paint mPaint = new Paint();
            mPaint.setColor(0x30000000);
            mPaint.setAlpha(defaultAlpha);
            final int[] mRadius = {0};
            changeDatas.add(new ChangeData(touchX, touchY, rediu, mPaint));
            final Iterator<ChangeData> itear = changeDatas.iterator();
            final ChangeData data = itear.next();
            final float maxRadius = (float) Math.pow(Math.pow(width, 2) + Math.pow(height, 2), 0.5);
            final ObjectAnimator anim1 = ObjectAnimator//
                    .ofFloat(this, "draw", 0F, maxRadius)//
                    .setDuration(500);//
            final ObjectAnimator anim2 = ObjectAnimator//
                    .ofFloat(this, "alpha", defaultAlpha, 0)//
                    .setDuration(200);//


            anim1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    float changeValue = (float) animation.getAnimatedValue();
                    mRadius[0] = (int) (changeValue);
                    data.setRedic(mRadius[0]);

                    invalidateSelf();
                }
            });
            anim2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    float changeValue = (float) animation.getAnimatedValue();
                    mPaint.setAlpha((int) changeValue);
                    data.setPaint(mPaint);
                    invalidateSelf();
                }
            });
            anim1.start();
            anim2.start();

        }
    }
//
//
//            touchX = event.getX();
//            touchY = event.getY();
//
//            Paint paint=new Paint();
//            paint.setColor(0x30000000);
//            paint.setAlpha(defaultAlpha);
//            int rediu=0;
//
//
//
//            final float mRadius= (float) Math.pow(Math.pow(width,2)+Math.pow(height,2),0.5);
//            final ObjectAnimator anim1 = ObjectAnimator//
//                    .ofFloat(this, "draw", 0F,  mRadius)//
//                    .setDuration(500);//
//            final ObjectAnimator anim2 = ObjectAnimator//
//                    .ofFloat(this, "alpha", defaultAlpha,  0)//
//                    .setDuration(200);//
//            anim1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
//            {
//                @Override
//                public void onAnimationUpdate(ValueAnimator animation)
//                {
//                    float changeValue= (float) animation.getAnimatedValue();
//                    rediu = (int) (changeValue);
//                    invalidateSelf();
//            }
//            });
//
//            anim2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
//            {
//                @Override
//                public void onAnimationUpdate(ValueAnimator animation)
//                {
//                    float changeValue= (float) animation.getAnimatedValue();
//                    mAlpha =  changeValue;
//                    mPaint.setAlpha((int) mAlpha);
//                    invalidateSelf();
//                }
//            });
//
//            set.play(anim2).after(anim1);
//
//            set.start();
//            set.addListener(new Animator.AnimatorListener() {
//                @Override
//                public void onAnimationStart(Animator animation) {
//
//                }
//
//                @Override
//                public void onAnimationEnd(Animator animation) {
//
//                }
//
//                @Override
//                public void onAnimationCancel(Animator animation) {
//                    mPaint.setAlpha(0);
//                    radius=0;
//                    invalidateSelf();
//                    CCLog.e("set  onAnimationCancel");
//
//                }
//
//                @Override
//                public void onAnimationRepeat(Animator animation) {
//
//                }
//            });
//
//        }


    public void setWH(int width, int height) {
        this.width = width;
        this.height = height;
    }

}
