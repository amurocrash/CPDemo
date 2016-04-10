package com.amuro.cpdemo;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.amuro.gamesdk.FileUtil;
import com.amuro.gamesdk.SDKManager;

import java.io.File;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.bt).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                SDKManager.getInstance().login();
            }
        });

        findViewById(R.id.bt).setOnLongClickListener(new View.OnLongClickListener()
        {
            @Override
            public boolean onLongClick(View view)
            {

                return false;
            }
        });
    }
}
