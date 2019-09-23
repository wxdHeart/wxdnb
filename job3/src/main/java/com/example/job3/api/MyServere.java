package com.example.job3.api;

import com.example.job3.bean.RootBeans;
import com.example.job3.bean.RootBeansTab;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;



public interface MyServere {

    @GET("yunxue_app_api/content/getData/30/66597/1/10")
    Observable<RootBeans> getPerson();

    @GET("yunxue_app_api/teacher/getTeacherPower/{ID}")
    Observable<RootBeansTab> getData(@Path("ID") int id);
}
