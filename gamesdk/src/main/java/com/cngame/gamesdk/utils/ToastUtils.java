package com.cngame.gamesdk.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by user on 2016/4/9.
 */
public class ToastUtils
{
    public static void show(Context context, String msg)
    {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
}
