package com.example.commonlibrary.model;

import com.example.commonlibrary.utils.SDCardUtils;

import java.io.File;

/**
 * Created by henryzheng on 2017/2/27.
 */

public class CommonContants {
    public static final  String crashLogDir= SDCardUtils.getEstentCacheDir()+ File.separator+"crashLogDir";

    public static final  String crashLogFile=crashLogDir +File.separator+"crash.log";
    public static final  String launchAction="launchAction";


}
