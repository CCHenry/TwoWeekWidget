package com.example.cctransformtextview;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by henryzheng on 2017/3/1.
 */

public class TransformTextView extends TextView {
    Activity context;
    private boolean isFinsh = false;
    private String orignStr = "";
    private String orignStrKey;

    public TransformTextView(Context context) {
        super(context, null);
    }

    public TransformTextView(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
    }

    public TransformTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        context = (Activity) context;


    }

    @Override
    public void append(CharSequence text, int start, int end) {
        super.append(text, start, end);
    }


    public void setSlowText(String text) {
        setText("");
        orignStr = text.toString();
        String appendChar = "";
        for (int i = 0; i < orignStr.length(); i++) {
            final int finalI = i;
            appendChar = orignStr.charAt(finalI) + "";
            final String tempAppendChar=appendChar;
            new Thread() {
                @Override
                public void run() {
                    synchronized (handler){
                    Message msg = new Message();
                    Bundle bundle = new Bundle();
                    bundle.putString(orignStrKey, tempAppendChar);
                    msg.setData(bundle);
                    try {
                        sleep(30);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    handler.sendMessage(msg);
                }}

            }.start();
        }

//        @Override
//        public void setText ( final CharSequence text, final BufferType type){
//            super.setText(text, type);
//        if (isFinsh) {
//            isFinsh = false;
//            return;
//        } else {
//            orignStr = text.toString();
//            String changeStr = "";
//            for (int i = 0; i < orignStr.length(); i++) {
//                final int finalI = i;
//                changeStr = changeStr + orignStr.charAt(finalI) + "";
//                final String finalChangeStr = changeStr;
//                CCLog.e("out:" + "i=" + i + ",finalChangeStr:" + changeStr);
//                if (finalChangeStr.equals(orignStr)){
//                    isFinsh=true;
//                    CCLog.e("isFinsh");
//                }
//                new Thread() {
//                    @Override
//                    public void run() {
//                        Message msg=new Message();
//                        Bundle bundle=new Bundle();
//                        bundle.putString(orignStrKey, finalChangeStr);
//                        msg.setData(bundle);
//
//                        try {
//                            sleep(1000);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//
//                        handler.sendMessage(msg);
//                }
//
//            }.start();
//        }

//    }
        }

        Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                Bundle bundle = msg.getData();
                String appenChar = bundle.getString(orignStrKey);
                append(appenChar);
            }
        };


//    public void setText(String text){
//        int time=0;
//        if(text!=null&&!text.isEmpty()){
//            char[] characters=text.toCharArray();
//            for (char c : characters) {
//                final TextView tv1=new TextView(context);
//                /**遍历每个字符串的每个字符，生成一个TextView并设置他动画**/
//                tv1.setText(String.valueOf(c));
//                /**设置字体大小**/
//                tv1.setTextSize(30);
//                /**设置字体颜色**/
//                tv1.setTextColor(Color.RED);
//                /**创建一个Handler实例**/
//                Handler handler=new Handler();
//                //每隔多少秒播放下一个TextView的动画
//                handler.postDelayed(new Runnable() {
//
//                    @Override
//                    public void run() {
//                        /**将tv添加到view中然后设置动画**/
//                        addview(tv1);
//                        tv1.setAnimation(animation);
//                    }
//                }, time);
//                /**设置时间间隔**/
//                time+=duration;
//            }
//        }
//    }
    }
