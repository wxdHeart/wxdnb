package com.example.myappday01;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;


/**
 * A simple {@link Fragment} subclass.
 */
public class ShowFragment extends Fragment {


    private View view;
    private ImageView mVpImg;

    public ShowFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_show, container, false);
        initView(inflate);
        return inflate;
    }

    private void initView(View inflate) {
        mVpImg = (ImageView) inflate.findViewById(R.id.vpImg);
        Bundle b = getArguments();
        String imgUrl = (String) b.get("imgUrl");

        Glide.with(getActivity()).load(imgUrl).into(mVpImg);
    }
}
