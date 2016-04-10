package com.cngame.gamesdk.P.login;

import com.cngame.gamesdk.M.client.Event;
import com.cngame.gamesdk.P.AbsPresenter;
import com.cngame.gamesdk.V.login.ILoginV;

/**
 * Created by user on 2016/4/10.
 */
public class LoginPresenter extends AbsPresenter<ILoginV>
{

    public static LoginPresenter getInstance()
    {
        return getInstance(LoginPresenter.class);
    }


    protected LoginPresenter()
    {
        super();
        clientProcessor.registerEventSubscriber(this);
    }

    public void login()
    {
        clientProcessor.sendCommond("login");
    }

    @Event(Event.EventType.LOGIN_SUCCEED)
    private void notifyLoginSucceed()
    {

        for(ILoginV v : vList)
        {
            if (v != null)
            {
                v.onLoginSucceed();
            }
        }
    }

    @Event(Event.EventType.LOGIN_FAILED)
    private void notifyLoginFailed()
    {
        for(ILoginV v : vList)
        {
            if (v != null)
            {
                v.onLoginFailed();
            }
        }
    }
}
