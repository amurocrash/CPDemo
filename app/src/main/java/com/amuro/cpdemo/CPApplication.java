package com.amuro.cpdemo;

import android.app.Application;

import com.amuro.gamesdk.SDKManager;

public class CPApplication extends Application
{

    @Override
    public void onCreate()
    {
        super.onCreate();

        String apiKey = "1003";
        String appId = "1";
        String apiPassword = "B97FED4E9994E33353DFAA8A31428E11BD7AE59";

        SDKManager.getInstance().init(
                CPApplication.this, apiKey, appId, apiPassword);

    }

    @Override
    public void onTerminate()
    {
        SDKManager.getInstance().destroy();
        super.onTerminate();
    }
}
