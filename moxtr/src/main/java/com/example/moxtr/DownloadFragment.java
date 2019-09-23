package com.example.moxtr;


import android.Manifest;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.moxtr.bean.MySer;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;


/**
 * A simple {@link Fragment} subclass.
 */
public class DownloadFragment extends Fragment implements View.OnClickListener {


    private View view;
    private ProgressBar mPb;
    /**
     * 开始下载
     */
    private Button mDownload;
    private TextView mShowProgress;

    public DownloadFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,

                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_download, container, false);
        initView(view);

        return view;

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getMsg(MsgEvent msgEvent) {
        mPb.setMax((int)msgEvent.getMax());
        mPb.setProgress((int)msgEvent.getProgress());
        float v = (float) msgEvent.getProgress() / msgEvent.getMax();
        float v1 = Math.round(v * 100);
        mShowProgress.setText("当前下载进度："+v1+"%");
        Log.i("111111", "当前下载进度：" + v1 + "%");
        if ((int) msgEvent.getMax() == (int) msgEvent.getProgress()) {
            Toast.makeText(getActivity(), "下载完成", Toast.LENGTH_SHORT).show();
        }
    }


    private void initView(View view) {
        mPb = (ProgressBar) view.findViewById(R.id.pb);
        mDownload = (Button) view.findViewById(R.id.download);
        mDownload.setOnClickListener(this);
        mShowProgress = (TextView) view.findViewById(R.id.showProgress);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.download:
                new Thread(){
                    @Override
                    public void run() {
                        super.run();
                        initData();
                    }
                }.start();


                break;
        }
    }

    private void initData() {
        new Thread() {
            @Override
            public void run() {
                super.run();

                File externalStorageDirectory = Environment.getExternalStorageDirectory();
                File ss = new File(externalStorageDirectory, "ss");

                OkHttpClient build = new OkHttpClient.Builder().build();
                Request build1 = new Request.Builder()
                        .get()
                        .url("https://cdn.banmi.com/banmiapp/apk/banmi_330.apk")
                        .build();
                Call call = build.newCall(build1);
                try {
                    Response execute = call.execute();
                    long l = execute.body().contentLength();
                    InputStream inputStream = execute.body().byteStream();
                    byte[] bytes = new byte[1024];
                    int len = inputStream.read(bytes);
                    FileOutputStream fileOutputStream = new FileOutputStream(ss);
                    while (len != -1) {
                        fileOutputStream.write(bytes, 0, len);
                        len = inputStream.read(bytes);
                        long length = ss.length();
                        final int round = (int) (length * 100 / l);
                        Log.i("tag", round + "");
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mPb.setProgress(round);
                                mShowProgress.setText("已下载" + round + "%");
                                if (round == 100) {
                                    mShowProgress.setText("下载完成");
                                    NotificationManager systemService = (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
                                    Notification build2 = new NotificationCompat.Builder(getActivity())
                                            .setContentTitle("下载进度")
                                            .setContentText("已下载完成")
                                            .setSmallIcon(R.mipmap.ic_launcher_round)
                                            .build();
                                    systemService.notify(1, build2);

                                }
                            }
                        });
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
