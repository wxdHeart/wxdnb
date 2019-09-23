package com.example.moxtr.bean;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;

public interface MySer {
    public static final String APK_URL = "http://cdn.banmi.com/";
    public String url_r="http://gank.io/api/";
    @GET("data/%E7%A6%8F%E5%88%A9/10/1")
    Observable<User> getUser();
    @GET("banmiapp/apk/banmi_330.apk")
    Observable<ResponseBody> downloadApk();
}
