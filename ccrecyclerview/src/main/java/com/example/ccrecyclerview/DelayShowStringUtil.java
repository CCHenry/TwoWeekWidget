package com.example.ccrecyclerview;

import android.app.Activity;

import java.lang.ref.SoftReference;

/**
 * Created by henryzheng on 2017/3/21.
 */

public class DelayShowStringUtil {
    public interface callback{
        void startCallback(String text);

        void callback(String text);

        void finshCallback(String text);

    }

    public static DelayShowStringUtil delayShowTextViewUtil;

    public static DelayShowStringUtil getInstance(){
        if(delayShowTextViewUtil==null){
            synchronized (DelayShowStringUtil.class){
                if (delayShowTextViewUtil==null){
                    delayShowTextViewUtil=new DelayShowStringUtil();
                }
            }
        }
        return delayShowTextViewUtil;
    }

    public void show(final Activity activity, final String text,long delayTime1, callback callback){
        SoftReference softActivity=new SoftReference(activity);
        new  DelayShowText(softActivity,text,delayTime1,  callback).start();
    }

   private class DelayShowText extends Thread{
        private final callback callback;
        private final long delayTime;
        private  SoftReference softActivity;
        private final String text;
        public DelayShowText(final SoftReference softActivity, final String text,long delayTime, callback callback){
            this.softActivity=softActivity;
            this.text=text;
            this.callback=callback;
            this.delayTime=delayTime;
        }
        @Override
        public  void run() {
            String changeText="";
            for (int i=0;i<text.length();i++){
                try {
                    sleep(delayTime/text.length());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                changeText+=text.charAt(i);
                final String finalChangeText = changeText;
                ((Activity)(softActivity.get())).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        callback.startCallback(finalChangeText);
                        callback.callback(finalChangeText);
                    }
                });
                if (i==text.length()-1){
                    callback.finshCallback(finalChangeText);

                }
            }
        }
    }

}
