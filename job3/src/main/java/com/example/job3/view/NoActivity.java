package com.example.job3.view;

import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

import com.example.job3.R;


public class NoActivity extends AppCompatActivity {

/*
    private VideoView mVideoview;
    private ImageView mImg;
*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        initView();

    }

    private void initView() {
      /*  mVideoview = (VideoView) findViewById(R.id.videoview);
        mImg = (ImageView) findViewById(R.id.img);
        Uri parse = Uri.parse("https://yunxue-bucket.oss-cn-shanghai.aliyuncs.com/classfile/0/从技术走向管理/001.从技术到管理_第1节_从技术到管理的内外部因素.mp4");
        mVideoview.setVideoURI(parse);
        mVideoview.start();

        MediaController mediaController = new MediaController(this);
        mVideoview.setMediaController(mediaController);
        mediaController.setAnchorView(mVideoview);*/

    }
}
