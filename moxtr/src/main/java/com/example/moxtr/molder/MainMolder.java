package com.example.moxtr.molder;

import com.example.moxtr.bean.MySer;
import com.example.moxtr.bean.User;
import com.example.moxtr.views.MainViews;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainMolder implements MolderMain{
    @Override
    public void getData(final MainViews mainViews) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MySer.url_r)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        MySer mySer = retrofit.create(MySer.class);
        Observable<User> user = mySer.getUser();
        user.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<User>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(User user) {
                        mainViews.onSucce(user);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
