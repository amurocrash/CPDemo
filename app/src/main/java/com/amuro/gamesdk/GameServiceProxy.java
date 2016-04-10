package com.amuro.gamesdk;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.lang.reflect.Constructor;

/**
 * Created by user on 2016/4/9.
 */
public class GameServiceProxy extends Service
{
    @Nullable
    @Override
    public IBinder onBind(Intent intent)
    {
        Log.e("doublex", "onbind");

        return remoteService.onBind(intent);
    }

    private SDKClassManager sdkClassManager;
    private Class<? extends Service> remoteServiceClass;
    private Service remoteService;

    @Override
    public void onCreate()
    {
        sdkClassManager = new SDKClassManager(this);
        try
        {
            remoteServiceClass =
                    (Class<? extends Service>) sdkClassManager.getSDKClass(
                            "com.cngame.gamesdk.M.server.GameService");

            Constructor<? extends Service> serviceConstructor = remoteServiceClass
                    .getConstructor(new Class[] {});
            remoteService = serviceConstructor.newInstance(new Object[] {});

            remoteServiceClass.getMethod(
                    "setContext", Context.class).invoke(remoteService, this);

            remoteService.onCreate();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        remoteService.onStartCommand(intent, flags, startId);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy()
    {
        remoteService.onDestroy();
        super.onDestroy();
    }


}
