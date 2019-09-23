package com.example.myappday01;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.myappday01.bean.User;

import java.util.ArrayList;

public class MyBors extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        ArrayList<User.ResultsEntity> date = (ArrayList<User.ResultsEntity>) intent.getSerializableExtra("date");
        Intent it1 = new Intent(context, Main2Activity.class);
        it1.putExtra("pros",date);
      //  it1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(it1);
    }
}
