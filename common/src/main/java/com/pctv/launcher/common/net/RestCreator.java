package com.pctv.launcher.common.net;

import android.util.Log;

import com.pctv.launcher.common.app.ConfigType;
import com.pctv.launcher.common.app.Launcher;
import com.pctv.launcher.common.net.rx.RxRestService;

import java.util.ArrayList;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * 进行Retrofit配置
 * Created by Gentleman on 2018/6/17.
 */

public class RestCreator {

    //参数单例模式
    private static final class ParamsHolder{
        private static final WeakHashMap<String,Object> PARAMS = new WeakHashMap<>();
    }

    public static WeakHashMap<String,Object> getParams(){
        return ParamsHolder.PARAMS;
    }

    //RetrofitClient配置
    private static final class RetrofitHolder{
        private static final String BASE_URL = Launcher.getConfiguration(ConfigType.API_HOST);


        private static final Retrofit RETROFIT_CLIENT = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(OkHttpHolder.CLIENT)
                .build();
    }



    private static final class OkHttpHolder{
        private static final int TIME_OUT = 60;
        private static final OkHttpClient.Builder BUILDER = new OkHttpClient.Builder();
        private static final ArrayList<Interceptor> INTERCEPTORS = Launcher.getConfiguration(ConfigType.INTERCEPTOR);
        private static OkHttpClient.Builder addInterceptor(){
            if (INTERCEPTORS!=null&&INTERCEPTORS.size()>0){
                for (Interceptor interceptor : INTERCEPTORS) {
                    BUILDER.addInterceptor(interceptor);
                }
            }
            return BUILDER;
        }

        private static final OkHttpClient CLIENT =
                addInterceptor()
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .build();
    }

   private static final class ServiceHolder{
       private static final RxRestService RX_REST_SERVICE = RetrofitHolder.RETROFIT_CLIENT.create(RxRestService.class);
   }


   public static RxRestService getRxService(){
       return ServiceHolder.RX_REST_SERVICE;
   }







}
