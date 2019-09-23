package com.example.tab;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TabLayout mMytab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mMytab = (TabLayout) findViewById(R.id.mytab);
        mMytab.addTab(mMytab.newTab().setText("首页"));
        mMytab.addTab(mMytab.newTab().setText("首页"));
        mMytab.addTab(mMytab.newTab().setText("首页"));

    }
}
