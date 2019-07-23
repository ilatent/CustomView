package com.lihao.customview.util;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.ColorRes;

/**
 * Created by Hao on 2019/7/23.
 * Describe
 */
public class GlobalUtils {


    /**
     * 获取颜色值
     *
     * @param resId
     * @return
     */
    public static int getColor(Context context, @ColorRes int resId) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return context.getColor(resId);
        } else {
            return context.getResources().getColor(resId);
        }
    }

    /**
     * 获取颜色值
     *
     * @param resId
     * @return
     */
    public static int getColor(@ColorRes int resId) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return Utils.getApp().getColor(resId);
        } else {
            return Utils.getApp().getResources().getColor(resId);
        }
    }

    /**
     * 改变颜色的透明度
     *
     * @param resId
     * @param alpha
     * @return
     */
    public static int setAlpha(@ColorRes int resId, int alpha) {
        int color = getColor(resId);
        int red = Color.red(color);
        int green = Color.green(color);
        int blue = Color.blue(color);
        return Color.argb(alpha, red, green, blue);
    }
}
