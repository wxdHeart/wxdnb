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

public class HomeRecyclerAdapter extends RecyclerView.Adapter<HomeRecyclerAdapter.ViewHolder>{

    private Context context;
    private List<RootBeans.BodyBean.ResultBean> result;

    public HomeRecyclerAdapter(Context context, List<RootBeans.BodyBean.ResultBean> result) {
        this.context = context;
        this.result = result;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.recylayout, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Glide.with(context).load(result.get(position).getTeacherPic()).circleCrop().into(holder.img);
        holder.myname.setText(result.get(position).getTeacherName());
        holder.myintroduce.setText(result.get(position).getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                u.OnClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView img;
        TextView myname;
        TextView myintroduce;
        Button but;

        public ViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            myname = itemView.findViewById(R.id.myname);
            myintroduce = itemView.findViewById(R.id.myintroduce);
            but = itemView.findViewById(R.id.but);
        }
    }

    public User u;

    public void setU(User u) {
        this.u = u;
    }

    public interface User{
        void OnClick(int position);
    }
}
