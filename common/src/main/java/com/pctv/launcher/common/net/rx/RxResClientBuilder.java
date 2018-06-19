package com.pctv.launcher.common.net.rx;

import android.content.Context;

import com.pctv.launcher.common.net.RestCreator;

import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.RequestBody;

/**
 * Created by Gentleman on 2018/6/17.
 */

public class RxResClientBuilder {

    private String mUrl = null;
    private final WeakHashMap<String,Object> PARAMS = new WeakHashMap<>();
    private RequestBody mRequestBody = null;
    private Context mContext = null;

    public RxResClientBuilder url(String url){
        this.mUrl = url;
        return this;
    }

    public RxResClientBuilder params(String key,Object value){
        PARAMS.put(key,value);
        return this;
    }

    public RxResClientBuilder params(WeakHashMap<String,Object> params){
        PARAMS.putAll(params);
        return this;
    }

    public RxResClientBuilder requestBody(RequestBody requestBody){
        this.mRequestBody = requestBody;
        return this;
    }

    //Context可以在后面扩展Loading框架的时候进行初始化

    public RxResClient build(){
        return new RxResClient(mUrl,PARAMS,mRequestBody,mContext);
    }
}
