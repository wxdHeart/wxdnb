package com.example.myappday01.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class FragmentAdap extends FragmentPagerAdapter {
    private ArrayList<Fragment> list;

    public FragmentAdap(FragmentManager fm, ArrayList<Fragment> list) {
        super(fm);
        this.list = list;
    }

    public FragmentAdap(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }
}
