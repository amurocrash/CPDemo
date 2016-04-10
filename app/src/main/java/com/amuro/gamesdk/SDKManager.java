package com.amuro.gamesdk;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import java.lang.reflect.Method;

/**
 * Created by user on 2016/4/7.
 */
public class SDKManager
{
    private SDKManager(){}

    private static SDKManager instance = new SDKManager();

    public static SDKManager getInstance()
    {
        return instance;
    }

    private Context context;
    private Handler handler;

    public void init(Context context, final String apiKey, final String appId, final String apiPassword)
    {
        this.context = context;
        this.handler = new Handler();
        this.sdkClassManager = new SDKClassManager(context);

        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                try
                {
                    loadSDk(apiKey, appId, apiPassword);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    private boolean isSDKLoaded = false;
    private SDKClassManager sdkClassManager;
    private Class<?> gameInterfaceClass;

    private void loadSDk(final String apiKey, final String appId, final String apiPassword) throws Exception
    {
        if(!isSDKLoaded)
        {
            String className = "com.cngame.gamesdk.GameInterface";
            gameInterfaceClass = sdkClassManager.getSDKClass(className);
            final Method method =
                    gameInterfaceClass.getMethod("init",
                            Context.class, String.class, String.class, String.class);

            handler.post(new Runnable()
            {
                @Override
                public void run()
                {
                    try
                    {
                        try
                        {
                            method.invoke(null, context, apiKey, appId, apiPassword);
                        }
                        catch (Exception e)
                        {
                            e.printStackTrace();
                        }

                        isSDKLoaded = true;
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }

                }
            });

            

        }
    }

    public void login()
    {
        try
        {
            Method method = gameInterfaceClass.getMethod("login");
            method.invoke(null);
        }
        catch (Exception e)
        {

        }
    }

    public void doBilling()
    {
        try
        {
            Method method = gameInterfaceClass.getMethod("doBilling");
            method.invoke(null);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void destroy()
    {
        try
        {
            Method method = gameInterfaceClass.getMethod("destroy");
            method.invoke(null);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
