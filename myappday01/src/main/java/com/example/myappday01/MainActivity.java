package com.example.myappday01;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.example.myappday01.adapters.RecyAdap;
import com.example.myappday01.bean.Myservice;
import com.example.myappday01.bean.User;

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

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    /**
     * 户外花朵识别系统
     */
    private TextView mMytvtoo;
    private Toolbar mMytoolder;
    private RecyclerView mMyrecy;
    private LinearLayout mMyll;
    private NavigationView mMynv;
    private DrawerLayout mMydl;
    private ArrayList<User.ResultsEntity> list;
    private RecyAdap recyAdap;
    private ImageView mImg1;
    private ImageView mImg2;
    private ImageView mImg;
    private MyBors myBors;

    //王兴达
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mMytvtoo = (TextView) findViewById(R.id.mytvtoo);
        mMytoolder = (Toolbar) findViewById(R.id.mytoolder);
        //  mMyrecy = (RecyclerView) findViewById(R.id.myrecy);
        mMyll = (LinearLayout) findViewById(R.id.myll);
        mMynv = (NavigationView) findViewById(R.id.mynv);
        mMydl = (DrawerLayout) findViewById(R.id.mydl);
        mImg1 = (ImageView) findViewById(R.id.img1);
        mImg2 = (ImageView) findViewById(R.id.img2);
        mMytoolder.setTitle("");
        setSupportActionBar(mMytoolder);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(MainActivity.this, mMydl, mMytoolder, R.string.app_name, R.string.app_name);
        toggle.syncState();
        mMydl.addDrawerListener(toggle);
        //  initRecy();
        initData();

        mImg = (ImageView) findViewById(R.id.img);
        mImg.setOnClickListener(this);
        mImg1.setOnClickListener(this);
        mImg2.setOnClickListener(this);
        list = new ArrayList<>();
    }

    private void initData() {
        Retrofit build = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://gitee.com/Haoxueren/server/raw/master/columns/final_exam_02.json/")
                .build();
        Myservice myservice = build.create(Myservice.class);
        Observable<User> date = myservice.getDate();
        date.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<User>() {



                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(User user) {
                        //加载图片
                        List<User.ResultsEntity> results = user.getResults();
                        String url = results.get(0).getUrl();
                        String url1 = results.get(1).getUrl();
                        String ss = results.get(2).getUrl();
                        Glide.with(MainActivity.this).load(url).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(mImg1);
                        Glide.with(MainActivity.this).load(url1).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(mImg2);
                        Glide.with(MainActivity.this).load(ss).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(mImg);
                        list.addAll(results);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void initRecy() {

        mMyrecy.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        mMyrecy.addItemDecoration(new DividerItemDecoration(MainActivity.this, 1));
        recyAdap = new RecyAdap(list, MainActivity.this);
        mMyrecy.setAdapter(recyAdap);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.img:
                Toast.makeText(this, "haha", Toast.LENGTH_SHORT).show();
                Intent wxd = new Intent("wxd");
                wxd.putExtra("date",list);
                sendBroadcast(wxd);
                break;
            case R.id.img1:

                break;
            case R.id.img2:
                break;
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        myBors = new MyBors();
        IntentFilter wxd = new IntentFilter("wxd");
        registerReceiver(myBors,wxd);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(myBors);
    }
}
