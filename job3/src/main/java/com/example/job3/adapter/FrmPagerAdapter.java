package com.example.job3.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.job3.bean.RootBeansTab;

import java.util.ArrayList;
import java.util.List;




public class FrmPagerAdapter extends FragmentPagerAdapter{

    private ArrayList<Fragment> fragments;
    private List<RootBeansTab.BodyBean.ResultBean> result1;
    public FrmPagerAdapter(FragmentManager fm,ArrayList<Fragment> fragments,List<RootBeansTab.BodyBean.ResultBean> result1) {
        super(fm);
        this.fragments = fragments;
        this.result1 = result1;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

}
