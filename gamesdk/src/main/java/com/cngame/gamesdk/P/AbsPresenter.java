package com.cngame.gamesdk.P;

import com.cngame.gamesdk.GameSDKCore;
import com.cngame.gamesdk.M.client.ClientProcessor;
import com.cngame.gamesdk.V.IV;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Amuro on 2016/3/24.
 */
public abstract class AbsPresenter<V extends IV>
{
    public static <P extends AbsPresenter> P getInstance(Class<? extends AbsPresenter> clazz)
    {
        return PresenterManager.getPresenter(clazz);
    }

    protected ClientProcessor clientProcessor;

    protected AbsPresenter()
    {
        clientProcessor = GameSDKCore.getInstance().getClientProcessor();
        vList = new ArrayList<>();
    }

    protected List<V> vList;

    public void addView(V view)
    {
        vList.add(view);
    }

    public void removeView(V view)
    {
        vList.remove(view);
    }

}
