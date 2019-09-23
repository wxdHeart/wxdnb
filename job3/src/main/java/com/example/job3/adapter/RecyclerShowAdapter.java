package com.example.job3.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.job3.R;
import com.example.job3.bean.RootBeans;

import java.util.List;


public class RecyclerShowAdapter extends RecyclerView.Adapter<RecyclerShowAdapter.ViewHolder>{

    private Context context;
    private List<RootBeans.BodyBean.ResultBean> result;
    private int index;

    public RecyclerShowAdapter(Context context, List<RootBeans.BodyBean.ResultBean> result,int index) {
        this.context = context;
        this.result = result;
        this.index = index;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context,R.layout.recylayout,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        position = index;
        Glide.with(context).load(result.get(position).getTeacherPic()).circleCrop().into(holder.img);
        holder.myname.setText(result.get(position).getTeacherName());
        holder.myintroduce.setText(result.get(position).getTitle());

    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView img;
        TextView myname;
        TextView myintroduce;
        TextView tv_c;
        Button but;

        public ViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            myname = itemView.findViewById(R.id.myname);
            myintroduce = itemView.findViewById(R.id.myintroduce);
            but = itemView.findViewById(R.id.but);
        }
    }
}
