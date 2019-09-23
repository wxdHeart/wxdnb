package com.example.job3.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.job3.R;
import com.example.job3.adapter.HomeRecyclerAdapter;
import com.example.job3.bean.RootBeans;
import com.example.job3.present.MainPresenter;
import com.example.job3.present.Presenter;

import java.util.List;

public class MainActivity extends AppCompatActivity implements Presenter {
    private RecyclerView mRecycler;
    private MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mRecycler = (RecyclerView) findViewById(R.id.recycler);
        mRecycler.setLayoutManager(new LinearLayoutManager(this));
        mRecycler.addItemDecoration(new DividerItemDecoration(this,1));
        mainPresenter = new MainPresenter(this);
        mainPresenter.getModel();
    }

    @Override
    public void sendData(final List<RootBeans.BodyBean.ResultBean> result) {
        HomeRecyclerAdapter recyclerAdapter = new HomeRecyclerAdapter(this, result);
        mRecycler.setAdapter(recyclerAdapter);
        recyclerAdapter.setU(new HomeRecyclerAdapter.User() {
            @Override
            public void OnClick(int position) {
                RootBeans.BodyBean.ResultBean resultBean = result.get(position);
                int id = resultBean.getID();
                Intent intent = new Intent(MainActivity.this, FirstActivity.class);
                intent.putExtra("index", position);
                intent.putExtra("id", id);
                startActivity(intent);

            }
        });

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mainPresenter.detach();
    }
}
