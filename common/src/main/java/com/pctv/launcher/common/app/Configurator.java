package com.pctv.launcher.common.app;

import android.content.Context;
import android.util.Log;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.pctv.launcher.common.util.log.LauncherLogger;

import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.Interceptor;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * 用来进行信息初始化
 * Created by Gentleman on 2018/6/17.
 */

public class Configurator {

    //存储配置信息的HashMap集合
    private final static HashMap<Object, Object> CONFIG_HASH = new HashMap<>();
    private final static ArrayList<Interceptor> INTERCEPTORS = new ArrayList<>();

    //单例模式:1、私有构造方法 2、对外提供初始化方法 3、初始化实现过程
    private Configurator() {
        CONFIG_HASH.put(ConfigType.CONFIG_READY, false);
    }

    private static class Holder {
        private static final Configurator INSTANCE = new Configurator();
    }

    //获得单例模式
    public static Configurator getInstance() {
        return Holder.INSTANCE;
    }

    //写入网络请求时的默认请求网址
    public final Configurator withApiHost(String host) {
        CONFIG_HASH.put(ConfigType.API_HOST, host);
        return this;
    }

    //传入ApplicationContext
    public final Configurator withApplicationContext(Context context) {
        CONFIG_HASH.put(ConfigType.APPLICATION_CONTEXT, context);
        return this;
    }

    public final Configurator withLogInterceptor(HttpLoggingInterceptor.Level level) {
        HttpLoggingInterceptor.Logger logger = new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                LauncherLogger.d("okhttp", message);
            }
        };

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(logger);
        loggingInterceptor.setLevel(level);
        INTERCEPTORS.add(loggingInterceptor);
        CONFIG_HASH.put(ConfigType.INTERCEPTOR, INTERCEPTORS);
        return this;
    }


    //配置完成
    public final void configure() {
        Logger.addLogAdapter(new AndroidLogAdapter());
        CONFIG_HASH.put(ConfigType.CONFIG_READY, true);
    }


    private void checkConfiguration() {
        final boolean isReady = (boolean) CONFIG_HASH.get(ConfigType.CONFIG_READY);
        if (!isReady) {
            throw new RuntimeException("配置信息未配置好");
        }
    }

    public HashMap<Object, Object> getConfigurations() {
        return CONFIG_HASH;
    }

    //对外提供配置信息
    @SuppressWarnings("unchecked")
    public <T> T getConfiguration(Object key) {
        checkConfiguration();
        return (T) CONFIG_HASH.get(key);
    }


}
