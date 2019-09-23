package com.example.moxtr.Adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class FragmentPageAdap extends FragmentPagerAdapter {
    private ArrayList<Fragment> list;
    private Context context;

    public FragmentPageAdap(FragmentManager fm, ArrayList<Fragment> list, Context context) {
        super(fm);
        this.list = list;
        this.context = context;
    }

    public FragmentPageAdap(FragmentManager fm) {
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
