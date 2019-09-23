package com.example.moxtr;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.example.moxtr.Adapters.FragmentPageAdap;
import com.example.moxtr.views.ShowFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ViewPager mMyvp;
    private TabLayout mMytab;
    private ArrayList<Fragment> list;
    private FragmentPageAdap fragmentPageAdap;
    private Toolbar mMytoolder;
    /**
     * 首页
     */
    private TextView mTvname;

    //王兴达
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mMyvp = (ViewPager) findViewById(R.id.myvp);
        mMytab = (TabLayout) findViewById(R.id.mytab);
        mMytoolder = (Toolbar) findViewById(R.id.mytoolder);
        mTvname = (TextView) findViewById(R.id.tvname);
        mMytoolder.setTitle("");
        setSupportActionBar(mMytoolder);
        list = new ArrayList<>();
        ShowFragment showFragment = new ShowFragment();
        DownloadFragment downloadFragment = new DownloadFragment();
        list.add(showFragment);
        list.add(downloadFragment);
        fragmentPageAdap = new FragmentPageAdap(getSupportFragmentManager(), list, MainActivity.this);
        mMyvp.setAdapter(fragmentPageAdap);
        mMytab.setupWithViewPager(mMyvp);
        mMytab.getTabAt(0).setText("首页").setIcon(R.mipmap.ic_launcher);
        mMytab.getTabAt(1).setText("下载").setIcon(R.mipmap.ic_launcher);
        mMytab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 0:
                        mTvname.setText("首页");
                      break;
                        case 1:
                         mTvname.setText("下载");
                       break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
