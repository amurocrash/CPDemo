package com.cngame.gamesdk.M.client;

import android.os.Handler;
import android.os.SystemClock;

import com.cngame.gamesdk.P.login.LoginPresenter;
import com.cngame.gamesdk.utils.LogUtils;
import com.cngame.gamesdk.utils.MyUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by Amuro on 2016/4/9.
 */
public final class ClientProcessor
{
    private Socket mClientSocket;
    private PrintWriter mPrintWriter;

    private boolean isSDKAlive = true;
    private MessageDisposer msgDisposer;
    private Handler handler;

    public void init()
    {
        handler = new Handler();
        msgDisposer = new MessageDisposer();
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                connectToService();
            }
        }).start();

    }

    private void connectToService()
    {
        Socket socket = null;
        while (socket == null)
        {
            try
            {
                socket = new Socket("localhost", 8688);
                mClientSocket = socket;
                mPrintWriter = new PrintWriter(new BufferedWriter(
                        new OutputStreamWriter(socket.getOutputStream())), true);

                LogUtils.e("connect server success");
            }
            catch (IOException e)
            {
                SystemClock.sleep(1000);
                LogUtils.e("connect tcp server failed, retry...");
            }
        }

        try
        {
            // 接收服务器端的消息
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    socket.getInputStream()));
            while (isSDKAlive)
            {
                final String msg = br.readLine();
                LogUtils.e("receive :" + msg);
                handler.post(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        msgDisposer.disposeMessage(msg);
                    }
                });

            }

            LogUtils.e("Client socket quit...");
            MyUtils.close(mPrintWriter);
            MyUtils.close(br);
            socket.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void destroy()
    {
        isSDKAlive = false;

        if (mClientSocket != null)
        {
            try
            {
                mClientSocket.shutdownInput();
                mClientSocket.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    public void sendCommond(String commond)
    {
        if(mPrintWriter != null)
        {
            mPrintWriter.println(commond);
        }
    }

    public void registerEventSubscriber(Object subscriber)
    {
        msgDisposer.registerEventSubscriber(subscriber);
    }
}
