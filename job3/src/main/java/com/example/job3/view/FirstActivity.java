package com.example.job3.view;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.job3.R;
import com.example.job3.adapter.FrmPagerAdapter;
import com.example.job3.adapter.RecyclerShowAdapter;
import com.example.job3.api.MyServere;
import com.example.job3.bean.RootBeans;
import com.example.job3.bean.RootBeansTab;
import com.example.job3.fragment.ShowFragmenr;
import com.example.job3.present.MainPresenter;
import com.example.job3.present.Presenter;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class FirstActivity extends AppCompatActivity implements Presenter {

    private RecyclerView mRecycler;
    private int index;
    private TabLayout mTablayout;
    private ViewPager mViewpager;
    private int id;
    private ArrayList<RootBeans.BodyBean.ResultBean> data;
    public ArrayList<RootBeans.BodyBean.ResultBean> getData() {
        return data;
    }

    public TabLayout getmTablayout() {
        return mTablayout;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        initView();

    }

    private void initView() {
        data = new ArrayList<>();
        mRecycler = (RecyclerView) findViewById(R.id.recycler);
        mTablayout = (TabLayout) findViewById(R.id.tablayout);
        mViewpager = (ViewPager) findViewById(R.id.viewpager);
        mRecycler.setLayoutManager(new LinearLayoutManager(this));
        Intent intent = getIntent();
        index = intent.getIntExtra("index", 0);
        id = intent.getIntExtra("id", 0);
        Log.i("tag", id+"");
        MainPresenter mainPresenter = new MainPresenter(this);
        mainPresenter.getModel();

    }

    @Override
    public void sendData(final List<RootBeans.BodyBean.ResultBean> result) {
        data.addAll(result);
        RecyclerShowAdapter recyclerShowAdapter = new RecyclerShowAdapter(this, result, index);
        mRecycler.setAdapter(recyclerShowAdapter);

        new Thread(){
            @Override
            public void run() {
                super.run();
                Retrofit build = new Retrofit.Builder()
                        .baseUrl("https://api.yunxuekeji.cn/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .build();
                MyServere myServere = build.create(MyServere.class);
                Observable<RootBeansTab> data = myServere.getData(id);
                data.observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(new Observer<RootBeansTab>() {

                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(RootBeansTab rootBeansTab) {
                                List<RootBeansTab.BodyBean.ResultBean> result1 = rootBeansTab.getBody().getResult();
                                Log.i("tag", result1+"");
                                ArrayList<Fragment> fragments = new ArrayList<>();
                                for (int i = 0; i < result1.size(); i++) {
                                    ShowFragmenr showFragmenr = new ShowFragmenr();
                                    fragments.add(showFragmenr);
                                }
                                FrmPagerAdapter f = new FrmPagerAdapter(getSupportFragmentManager(), fragments, result1);
                                mViewpager.setAdapter(f);
                                mTablayout.setupWithViewPager(mViewpager);

                                for (int i = 0; i < result1.size(); i++) {
                                    mTablayout.getTabAt(i).setText(result1.get(i).getDescription());
                                }


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
