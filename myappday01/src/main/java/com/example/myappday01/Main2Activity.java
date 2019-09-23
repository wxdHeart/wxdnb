package com.example.myappday01;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.myappday01.adapters.FragmentAdap;
import com.example.myappday01.bean.User;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {

    private ViewPager mMyvp;
    private TextView mShow;
    private ArrayList<Fragment> list;
    private FragmentAdap fragmentAdap;
    /**
     * 返回
     */
    private Button mMybtt;
    private int page=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initView();
        final ArrayList<User.ResultsEntity> pros = (ArrayList<User.ResultsEntity>) getIntent().getSerializableExtra("pros");
        list = new ArrayList<>();
        String url = pros.get(0).getUrl();

        for (int i = 0; i < pros.size(); i++) {
            ShowFragment vpFragment = new ShowFragment();
            Bundle b = new Bundle();
            b.putString("imgUrl", pros.get(i).getUrl());
            vpFragment.setArguments(b);
            list.add(vpFragment);
            fragmentAdap = new FragmentAdap(getSupportFragmentManager(), list);
            mMyvp.setAdapter(fragmentAdap);
            showTv(1, pros.size());
            mMyvp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                    showTv(1 + position, pros.size());
                }


                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });
        }


    }

    private void showTv(int i, int size) {
        mShow.setText("第" + i + "张/共" + size + "张");
    }

    private void initView() {
        mMyvp = (ViewPager) findViewById(R.id.myvp);
        mShow = (TextView) findViewById(R.id.show);
        mMybtt = (Button) findViewById(R.id.mybtt);
        mMybtt.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.mybtt:
                startActivity(new Intent(Main2Activity.this,MainActivity.class));
                break;
        }
    }
}
