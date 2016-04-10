package com.cngame.gamesdk.M.client;

import android.os.Handler;
import android.os.Looper;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2016/4/10.
 */
public class MessageDisposer
{
    /****************************Event*****************************/
    protected List<Object> subscribers = new ArrayList<Object>();

    public void registerEventSubscriber(Object subscriber)
    {
        this.subscribers.add(subscriber);
    }

    public void removeEventSubscriber(Object subscriber)
    {
        int index = this.subscribers.indexOf(subscriber);

        if(index != -1)
        {
            this.subscribers.remove(index);
        }
    }

    protected void notifyEvent(Event.EventType eventType, Object... args)
    {
        for(Object subscriber : subscribers)
        {
            if(subscriber != null)
            {
                Method[] methods = subscriber.getClass().getDeclaredMethods();
                for (Method method : methods)
                {
                    Event event = method.getAnnotation(Event.class);
                    if (event != null)
                    {
                        if(eventType == event.value())
                        {
                            try
                            {
                                method.setAccessible(true);
                                method.invoke(subscriber, args);
                            }
                            catch (Exception e)
                            {

                            }
                        }
                    }
                }
            }
        }
    }

    public void disposeMessage(final String msg)
    {
        if(msg != null)
        {
            notifyEvent(Event.EventType.LOGIN_SUCCEED);
        }
    }


}
