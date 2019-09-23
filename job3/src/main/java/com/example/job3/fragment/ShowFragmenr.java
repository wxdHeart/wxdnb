package com.example.job3.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.job3.R;
import com.example.job3.adapter.IntroduceAdapter;
import com.example.job3.bean.RootBeans;
import com.example.job3.view.FirstActivity;
import com.example.job3.view.NoActivity;

import java.util.ArrayList;


public class ShowFragmenr extends Fragment {
    private View view;
    private TextView mTvA;
    private RecyclerView mTvB;
    private TextView mTvC;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.showfragment, null);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mTvA = (TextView) view.findViewById(R.id.tv_a);
        mTvB = (RecyclerView) view.findViewById(R.id.tv_b);
        mTvC = (TextView) view.findViewById(R.id.tv_c);
        FirstActivity activity = (FirstActivity) getActivity();
        mTvB.setLayoutManager(new LinearLayoutManager(getActivity()));
        ArrayList<RootBeans.BodyBean.ResultBean> data = activity.getData();


        IntroduceAdapter courseAdapter = new IntroduceAdapter(getActivity(), data);
        courseAdapter.setU(new IntroduceAdapter.User() {
            @Override
            public void onClick() {
                Toast.makeText(getContext(), "待完善", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getActivity(),NoActivity.class));
            }
        });
        mTvB.setAdapter(courseAdapter);
        TabLayout tabLayout = activity.getmTablayout();

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 0:
                        mTvA.setVisibility(View.VISIBLE);
                        mTvB.setVisibility(View.INVISIBLE);
                        mTvC.setVisibility(View.INVISIBLE);
                        break;

                    case 1:
                        mTvB.setVisibility(View.VISIBLE);
                        mTvA.setVisibility(View.INVISIBLE);
                        mTvC.setVisibility(View.INVISIBLE);
                        break;

                    case 2:
                        mTvA.setVisibility(View.INVISIBLE);
                        mTvB.setVisibility(View.INVISIBLE);
                        mTvC.setVisibility(View.VISIBLE);
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
