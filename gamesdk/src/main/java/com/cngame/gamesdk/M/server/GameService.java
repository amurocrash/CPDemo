package com.cngame.gamesdk.M.server;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.cngame.gamesdk.utils.MyUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by user on 2016/4/8.
 */
public class GameService extends Service
{
    private Context context;

    public void setContext(Context context)
    {
        this.context = context;
    }

    private boolean mIsServiceDestoryed = false;

    @Nullable
    @Override
    public IBinder onBind(Intent intent)
    {
        return ((Service)context).onBind(intent);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        new Thread(new TcpServer()).start();

        return super.onStartCommand(intent, flags, startId);
    }


    @Override
    public void onDestroy()
    {
        mIsServiceDestoryed = true;
        super.onDestroy();
    }

    private class TcpServer implements Runnable
    {

        @SuppressWarnings("resource")
        @Override
        public void run()
        {
            ServerSocket serverSocket = null;
            try
            {
                serverSocket = new ServerSocket(8688);
            }
            catch (IOException e)
            {
                System.err.println("establish tcp server failed, port:8688");
                e.printStackTrace();
                return;
            }

            while (!mIsServiceDestoryed)
            {
                try
                {
                    // 接受客户端请求
                    final Socket client = serverSocket.accept();
                    System.out.println("accept");
                    new Thread()
                    {
                        @Override
                        public void run()
                        {
                            try
                            {
                                responseClient(client);
                            }
                            catch (IOException e)
                            {
                                e.printStackTrace();
                            }
                        }

                    }.start();

                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }

            try
            {
                serverSocket.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    private void responseClient(Socket client) throws IOException
    {
        // 用于接收客户端消息
        BufferedReader in = new BufferedReader(new InputStreamReader(
                client.getInputStream()));
        // 用于向客户端发送消息
        PrintWriter out = new PrintWriter(new BufferedWriter(
                new OutputStreamWriter(client.getOutputStream())), true);

        while (!mIsServiceDestoryed)
        {
            String str = in.readLine();
            System.out.println("msg from client:" + str);
            if (str == null)
            {
                break;
            }
            String outMsg = "from service";
            out.println(outMsg);
            System.out.println("send :" + outMsg);
        }
        System.out.println("client quit.");
        // 关闭流
        MyUtils.close(out);
        MyUtils.close(in);
        client.close();
    }
}
