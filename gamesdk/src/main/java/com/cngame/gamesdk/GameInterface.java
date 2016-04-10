package com.cngame.gamesdk;

import android.content.Context;
import android.content.Intent;

import com.cngame.gamesdk.M.client.ClientProcessor;
import com.cngame.gamesdk.V.billing.BillingViewController;
import com.cngame.gamesdk.V.login.LoginViewController;
import com.cngame.gamesdk.utils.LogUtils;

/**
 * Created by user on 2016/4/7.
 */
public class GameInterface
{
    public static void init(
            final Context context, final String apiKey, final String appId, final String apiPassword)
    {
        GameSDKCore.getInstance().init(context, apiKey, appId, apiPassword);
    }

    public static void destroy()
    {
        GameSDKCore.getInstance().destroy();
    }

    public static void doBilling()
    {
        GameSDKCore.getInstance().doBilling();
    }

    public static void login()
    {
        GameSDKCore.getInstance().login();
    }


}
