package com.example.moxtr.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.example.moxtr.R;
import com.example.moxtr.bean.User;

import java.util.ArrayList;

public class RecyAdap extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<User.ResultsEntity> list;
    private Context context;



    public RecyAdap(ArrayList<User.ResultsEntity> list, Context context) {
        this.list = list;
        this.context = context;
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
 if (viewType==0){
              View view = View.inflate(context, R.layout.item, null);
              return new ViewHolder(view);
          }else {
              View view = View.inflate(context, R.layout.item1, null);
              return new ViewHolder1(view);
          }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        User.ResultsEntity resultsEntity = list.get(position);
        int type = getItemViewType(position);
        if (type==0){
            ViewHolder holder1 = (ViewHolder) holder;
            holder1.desc.setText(resultsEntity.getDesc());
            Glide.with(context).load(resultsEntity.getUrl())
                    .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                    .into(holder1.img);
        }else {
            ViewHolder1 holder1 = (ViewHolder1) holder;
            Glide.with(context).load(resultsEntity.getUrl())
                    .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                    .into(holder1.img1);
        }

    }

    @Override
    public int getItemViewType(int position) {
        if (position%3==0){
            return 0;
        }else {
            return 1;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView img;
        TextView desc;

        public ViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            desc = itemView.findViewById(R.id.desc);
        }
    }
    public class ViewHolder1 extends RecyclerView.ViewHolder {

        ImageView img1;

        public ViewHolder1(View itemView) {
            super(itemView);
            img1 = itemView.findViewById(R.id.img1);
        }
    }
}
