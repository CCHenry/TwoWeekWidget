package com.example.ccripplebutton;

import android.graphics.Paint;

/**
 * Created by henryzheng on 2017/3/1.
 */

public class ChangeData {
    Paint paint;
    int redic;
    float touchX;
    float touchY;

    public ChangeData(float touchX, float touchY, int redic,
                      Paint paint ) {
        this.paint = paint;
        this.redic = redic;
        this.touchX = touchX;
        this.touchY = touchY;
    }

    public Paint getPaint() {
        return paint;
    }

    public void setPaint(Paint paint) {
        this.paint = paint;
    }

    public int getRedic() {
        return redic;
    }

    public void setRedic(int redic) {
        this.redic = redic;
    }

    public float getTouchX() {
        return touchX;
    }

    public void setTouchX(float touchX) {
        this.touchX = touchX;
    }

    public float getTouchY() {
        return touchY;
    }

    public void setTouchY(float touchY) {
        this.touchY = touchY;
    }
}
