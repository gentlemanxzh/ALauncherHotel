package com.pctv.launcher.hotel;

import android.app.Application;

import com.pctv.launcher.common.app.Launcher;

import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by Gentleman on 2018/6/17.
 */

public class LauncherApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Launcher.init(this)
                .withApiHost("http://127.0.0.1")
                .withLogInterceptor(HttpLoggingInterceptor.Level.BASIC)
                .configure();
    }
}
