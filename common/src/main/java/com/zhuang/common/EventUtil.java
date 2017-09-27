package com.zhuang.common;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by zhuang on 2017/9/26.
 */

public class EventUtil {

    /**
     * 页面跳转
     * @param context
     * @param className
     */
    public static void open(Context context,String className){
        try {
            Class clazz = Class.forName(className);
            Intent intent = new Intent(context,clazz);
            context.startActivity(intent);
        } catch (ClassNotFoundException e) {
            Log.e("zhuang","未集成，无法跳转");
        }
    }

    /**
     * 页面跳转，可以传参，参数放在intent中，所以需要传入一个intent
     * @param context
     * @param className
     * @param intent
     */
    public static void open(Context context,String className,Intent intent){
        try {
            Class clazz = Class.forName(className);
            intent.setClass(context,clazz);
            context.startActivity(intent);
        } catch (ClassNotFoundException e) {
            Log.e("zhuang","未集成，无法跳转");
        }
    }
}
