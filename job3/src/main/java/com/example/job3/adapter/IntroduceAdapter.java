package com.example.job3.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.job3.R;
import com.example.job3.bean.RootBeans;

import java.util.ArrayList;

public class IntroduceAdapter extends RecyclerView.Adapter<IntroduceAdapter.ViewHolder>{

    private Context context;
    private ArrayList<RootBeans.BodyBean.ResultBean> data;
    private ImageView img;

    public ImageView getImg() {
        return img;
    }

    public IntroduceAdapter(ImageView img) {
        this.img = img;
    }

    public IntroduceAdapter(Context context, ArrayList<RootBeans.BodyBean.ResultBean> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context,R.layout.couserlayout,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tv.setText(data.get(position).getTitle());

        img = holder.img;
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                u.onClick();
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv;
        ImageView img;
        public ViewHolder(View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv);
            img = itemView.findViewById(R.id.img);
        }
    }

    public User u;

    public void setU(User u) {
        this.u = u;
    }

    public interface User{
        void onClick();
    }
}
