package com.example.moxtr;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.example.moxtr.bean.MySer;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

public class DownLoadUtil {
    private static String TAG = "DownLoadUtil";

    public static void retrofitDownLoad( final String savePath) {
/*        Cache cache = new Cache(new File("/storage/emulated/0/test1cache"),1024*1024*100);
        OkHttpClient okHttpClient = new OkHttpClient.Builder().cache(cache).build();*/


    }

    //保存文件的方法
    private static void saveFile(InputStream inputStream, String savePath, long max) {
        File file = new File(savePath);
        int len = 0;
        byte[] bytes = new byte[4096];//每次4k
        int count = 0;//记录下载的大小
        //创建保存文件的输出流,把读取的信息写入到此流中，达到写入到文件中保存的效果
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            while ((len = inputStream.read(bytes)) != -1){//循环读取数据
                fos.write(bytes,0,len);
                count += len;//把新下载的数据添加到总进度中
                EventBus.getDefault().post(new MsgEvent(max,count));
            }
            //下载完成
            Log.i(TAG, "下载完成");
        } catch (Exception e) {
            e.printStackTrace();

        }finally {
            try {
                if(fos != null)
                    fos.close();
                if(inputStream != null)
                    inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



}
