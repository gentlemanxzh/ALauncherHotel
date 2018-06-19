package com.pctv.launcher.common.net.rx;

import android.content.Context;

import com.pctv.launcher.common.net.HttpMethod;
import com.pctv.launcher.common.net.RestCreator;
import com.pctv.launcher.common.util.log.LauncherLogger;

import java.util.Map;
import java.util.WeakHashMap;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.POST;

/**
 * Created by Gentleman on 2018/6/17.
 */

public class RxResClient {

    private final   String URL;
    private final WeakHashMap<String,Object> PARAMS ;
    private final Context CONTEXT;
    private final RequestBody BODY;

    public RxResClient(String url, WeakHashMap<String, Object>params , RequestBody body, Context context) {
        this.URL = url;
        PARAMS = params;
        this.CONTEXT = context;
        this.BODY = body;
    }


    public static RxResClientBuilder builder(){
        return new RxResClientBuilder();
    }


    private Observable<String> request(HttpMethod method){
        final RxRestService service = RestCreator.getRxService();
        Observable<String> observable = null;

        switch (method){

            case GET:
                observable = service.get(URL,PARAMS);
                LauncherLogger.d("网络请求参数",PARAMS);
                break;

            case POST:
                observable = service.post(URL,PARAMS);
                break;

            default:
                break;
        }
        return observable;
    }

    public Observable<String> get(){
        return request(HttpMethod.GET);
    }

    public Observable<String> post(){
        return request(HttpMethod.POST);
    }




}
