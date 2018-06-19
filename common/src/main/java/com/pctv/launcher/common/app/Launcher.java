package com.pctv.launcher.common.app;

import android.content.Context;

import com.orhanobut.logger.Logger;
import com.pctv.launcher.common.app.ConfigType;
import com.pctv.launcher.common.app.Configurator;

import java.util.HashMap;

/**
 * Created by Gentleman on 2018/6/17.
 *  配置信息对外的一个入口
 */

public final class Launcher {

    public static Configurator init(Context context){
        Configurator.getInstance().withApplicationContext(context);
        return Configurator.getInstance();
    }

    //获取所有配置信息
    public static HashMap<Object,Object> getConfiguration(){
       return Configurator.getInstance().getConfigurations();
    }

    //获取ApplicationContext
    public static Context getApplicationContext(){
        return Configurator.getInstance().getConfiguration(ConfigType.APPLICATION_CONTEXT);
    }

    //根据key获取相对应的配置信息
    public static <T> T getConfiguration(Object key){
        return Configurator.getInstance().getConfiguration(key);
    }
}
