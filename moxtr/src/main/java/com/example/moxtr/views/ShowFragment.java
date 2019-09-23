package com.example.moxtr.views;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.moxtr.Adapters.RecyAdap;
import com.example.moxtr.R;
import com.example.moxtr.bean.User;
import com.example.moxtr.molder.MainMolder;
import com.example.moxtr.presenter.MainPresenter;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ShowFragment extends Fragment implements MainViews{


    private View view;
    private RecyclerView mMyrecy;
    private ArrayList<User.ResultsEntity> list;
    private RecyAdap recyAdap;

    public ShowFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_show, container, false);
        initView(inflate);
        MainPresenter mainPresenter = new MainPresenter(this, new MainMolder());
        mainPresenter.getOp();
        return inflate;
    }
//王兴达
    private void initView(View inflate) {
        mMyrecy = (RecyclerView) inflate.findViewById(R.id.myrecy);
        mMyrecy.setLayoutManager(new LinearLayoutManager(getContext()));
        list = new ArrayList<>();
        recyAdap = new RecyAdap(list, getContext());
        mMyrecy.setAdapter(recyAdap);
    }

    @Override
    public void onSucce(User user) {
        List<User.ResultsEntity> results = user.getResults();
        list.addAll(results);
        recyAdap.notifyDataSetChanged();
    }

    @Override
    public void onFlie(String str) {

    }
}
