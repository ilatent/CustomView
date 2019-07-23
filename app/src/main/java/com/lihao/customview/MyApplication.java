package com.lihao.customview;

import android.app.Application;

import com.lihao.customview.util.Utils;

/**
 * Created by Hao on 2019/7/23.
 * Describe
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(this);
    }
}
