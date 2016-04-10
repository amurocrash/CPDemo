package com.cngame.gamesdk;

import android.content.Context;
import android.content.Intent;

import com.cngame.gamesdk.M.client.ClientProcessor;
import com.cngame.gamesdk.V.billing.BillingViewController;
import com.cngame.gamesdk.V.login.LoginViewController;
import com.cngame.gamesdk.utils.LogUtils;

/**
 * Created by user on 2016/4/10.
 */
public class GameSDKCore
{
    private static GameSDKCore instance;

    public static GameSDKCore getInstance()
    {
        if(instance == null)
        {
            synchronized (GameSDKCore.class)
            {
                if(instance == null)
                {
                    instance = new GameSDKCore();
                }
            }
        }

        return instance;
    }

    private Context context;
    private ClientProcessor clientProcessor;

    public void init(
            Context context, final String apiKey, final String appId, final String apiPassword)
    {
        this.context = context;

        Intent intent = new Intent();
        intent.setAction("com.cngame.startService");
        intent.setPackage(context.getPackageName());
        context.startService(intent);

        clientProcessor = new ClientProcessor();
        clientProcessor.init();
    }

    public void destroy()
    {
        clientProcessor.destroy();

        instance = null;
    }

    public void doBilling()
    {
        BillingViewController billingViewController = new BillingViewController(context);
        billingViewController.show();
    }

    public void login()
    {
        LoginViewController loginViewController = new LoginViewController(context);
        loginViewController.show();
    }

    public ClientProcessor getClientProcessor()
    {
        return clientProcessor;
    }
}
































