package com.example.commonlibrary;

import android.app.Application;
import android.content.Context;

/**
 * Created by henryzheng on 2017/2/27.
 */

public class CommonApplication extends Application {
    static Context context;
    public static CommonApplication getInstance(){
        return (CommonApplication) context;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        CrashHandler crashHandler=CrashHandler.getInstance();
        crashHandler.init(this);
        context=getApplicationContext();
    }
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);


    }
}
