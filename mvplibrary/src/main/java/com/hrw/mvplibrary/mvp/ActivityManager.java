package com.hrw.mvplibrary.mvp;

import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author:MtBaby
 * @date:2017/09/20 14:56
 * @desc:
 */


public class ActivityManager {
    private static List<AppCompatActivity> activities = new ArrayList<>();

    /**
     * 添加Activity对象到集合，统一管理
     *
     * @param appCompatActivity
     */
    public static void addActivity(AppCompatActivity appCompatActivity) {
        activities.add(appCompatActivity);
    }

    /**
     * 移除Activity对象
     *
     * @param appCompatActivity
     */
    public static void removeActivity(AppCompatActivity appCompatActivity) {
        if (activities.contains(appCompatActivity) && !appCompatActivity.isFinishing()) {
            activities.remove(appCompatActivity);
        }
    }

    /**
     * 清理所有Activity对象
     */
    public static void removeAll() {
        for (AppCompatActivity appCompatActivity : activities) {
            if (!appCompatActivity.isFinishing()) {
                activities.remove(appCompatActivity);
                appCompatActivity.finish();
            }
        }
    }

}
