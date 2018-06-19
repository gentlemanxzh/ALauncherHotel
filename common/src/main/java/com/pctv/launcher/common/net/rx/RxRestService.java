package com.pctv.launcher.common.net.rx;


import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * Created by Gentleman on 2018/6/17.
 * 网络请求接口
 */

public interface RxRestService {

    @GET
    Observable<String> get(@Url String url, @QueryMap Map<String,Object> params);

    @POST
    Observable<String> post(@Url String url,@QueryMap Map<String,Object> params);

}
