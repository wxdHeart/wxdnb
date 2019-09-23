package com.example.job3.model;

import com.example.job3.api.MyServere;
import com.example.job3.bean.RootBeans;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainModel {

    private int id;

    public MainModel(int id) {
        this.id = id;
    }

    public void getData(final Model m){

        new Thread(){
            @Override
            public void run() {
                super.run();

                Retrofit build = new Retrofit.Builder()
                        .baseUrl("https://api.yunxuekeji.cn/")
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                MyServere myServere = build.create(MyServere.class);
                Observable<RootBeans> person = myServere.getPerson();
                person.observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(new Observer<RootBeans>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(RootBeans rootBeans) {
                                List<RootBeans.BodyBean.ResultBean> result = rootBeans.getBody().getResult();
                                m.sendData(result);
                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        });
            }
        }.start();
    }
}
