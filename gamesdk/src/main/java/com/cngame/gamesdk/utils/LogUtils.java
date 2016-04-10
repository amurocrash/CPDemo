package com.cngame.gamesdk.utils;

import android.util.Log;

/**
 * Created by user on 2016/4/8.
 */
public class LogUtils
{
    private static final String DEFAULT_TAG = "cngame";

    public static void e(String msg)
    {
        Log.e(DEFAULT_TAG, msg);
    }

    public static void e(String tag, String msg)
    {
        Log.e(tag, msg);
    }
}
