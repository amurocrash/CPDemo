package com.amuro.gamesdk;

import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.io.FilenameFilter;
import java.lang.reflect.Method;

import dalvik.system.DexClassLoader;

/**
 * Created by Amuro on 2016/4/9.
 */
public class SDKClassManager
{
    private Context context;
    private DexClassLoader sdkClassLoader;
    private String sdkJarPaths;
    private File optDir;
    private File sdkSoDir;
    private Class<?> gameInterfaceClass;

    public SDKClassManager(Context context)
    {
        this.context = context;
    }

    public Class<?> getSDKClass(String className) throws Exception
    {
        prepareFiles();

        sdkClassLoader = new DexClassLoader(
                sdkJarPaths,
                optDir.getAbsolutePath(),
                sdkSoDir.getAbsolutePath(),
                context.getClass().getClassLoader());

        return sdkClassLoader.loadClass(className);
    }

    private void prepareFiles()
    {
        File sdkJarDir = FileUtil.getDir(
                Environment.getExternalStorageDirectory() + "/gamesdk/core/");

        String[] jarPaths = sdkJarDir.list(new FilenameFilter()
        {
            @Override
            public boolean accept(File dir, String filename)
            {
                return filename.endsWith(".jar");
            }
        });

        sdkJarPaths = "";
        if (jarPaths != null)
        {
            for (int i = 0; i < jarPaths.length; i++)
            {
                if (i == 0)
                {
                    sdkJarPaths += sdkJarDir.getAbsolutePath() + "/" + jarPaths[i];
                }
                else
                {
                    sdkJarPaths += ":" + sdkJarDir.getAbsolutePath() + "/" + jarPaths[i];
                }
            }
        }

        optDir = FileUtil.getDir(context.getCacheDir().getAbsolutePath() + "/gamesdk/core");

        sdkSoDir = FileUtil.getDir(
                context.getFilesDir() + "/gamesdk/core/");
    }
}
